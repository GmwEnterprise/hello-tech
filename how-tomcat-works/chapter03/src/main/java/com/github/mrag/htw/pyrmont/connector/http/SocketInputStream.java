package com.github.mrag.htw.pyrmont.connector.http;

import org.apache.naming.StringManager;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class SocketInputStream extends InputStream {
    private static final byte CR = (byte) '\r';
    private static final byte LF = (byte) '\n';
    private static final byte SP = (byte) ' ';
    private static final byte HT = (byte) '\t';
    private static final byte COLON = (byte) ':';
    private static final int LC_OFFSET = 'A' - 'a';

    // 真实持有的输入流
    private final InputStream input;
    // 内部缓冲器
    private final byte[] buf;
    // 当前buffer中合法字符长度
    private int count;
    // buf中的当前位置
    private int pos;

    private final StringManager sm = StringManager
            .getManager("com.github.mrag.htw.pyrmont.connector.http");

    public SocketInputStream(InputStream input, byte[] buf) {
        this.input = input;
        this.buf = buf;
    }

    @Override
    public int read() throws IOException {
        if (pos >= count) {
            fill();
            if (pos >= count) return -1;
        }
        return buf[pos++];
    }

    // 这里看出该 SocketInputStream 不支持重读
    // 但是在重新fill以前是可以支持重读的
    // 逻辑上来说不应该给调用者重复读取
    private void fill() throws IOException {
        pos = 0; // 位置重置
        count = 0; // 清空buffer
        int nRead = input.read(buf, 0, buf.length); // 填充buffer
        if (nRead > 0) count = nRead; // 填充buffer合法长度
    }

    // 从输入流中读取出请求行信息，填充到参数指定的对象
    public void readRequestLine(HttpRequestLine requestLine) throws IOException {
        // 重置读取
        if (requestLine.methodEnd != 0) {
            requestLine.recycle();
        }

        // 检查空白行
        int chr;
        do {
            try {
                chr = read();
            } catch (IOException e) {
                chr = -1;
            }
        } while (chr == CR || chr == LF);
        if (chr == -1) {
            throw new EOFException(sm.getString("requestStream.readline.error"));
        }

        pos--; // 回退一格, 因为do while必然多走一格

        // 填充METHOD

        // 读取method最大需要读取的字节数
        int maxRead = requestLine.method.length;
        // 当前读取了多少字节
        int readCount = 0;

        // http请求头中第一个字符开始就是method标记，读到空格就结束了
        boolean space = false;

        while (!space) {
            // 如果当前读取的字符总数超过了requestLine.method的数组容量
            // 则为requestLine.method扩容
            if (readCount >= maxRead) {
                requestLine.method = extendArray(requestLine.method, maxRead, maxRead << 2,
                                                 HttpRequestLine.MAX_METHOD_SIZE);
                maxRead = requestLine.method.length;
            }

            // 如果当前buffer读完了，继续读取
            if (pos >= count) {
                if (read() == -1) {
                    throw new IOException(sm.getString("requestStream.readline.error"));
                }
                pos = 0;
            }

            // 如果当前字符是空格 读取完毕
            if (buf[pos] == SP) space = true;

            // 操作
            requestLine.method[readCount++] = (char) buf[pos++];
        }

        // 减去 1 是因为最后一个字符是空格。readCount不统计空格
        // pos不减去 1 则跳过空格，可以直接开始下面的uri填充
        requestLine.methodEnd = readCount - 1;

        // 填充URI

        maxRead = requestLine.uri.length;
        readCount = 0;
        space = false;

        // HTTP/0.9 协议标识，这种最早的协议不支持协议头与请求头，uri读完就结束了
        boolean eol = false;

        while (!space) {
            // 扩容
            if (readCount >= maxRead) {
                requestLine.uri = extendArray(requestLine.uri, maxRead, maxRead << 2,
                                              HttpRequestLine.MAX_URI_SIZE);
                maxRead = requestLine.uri.length;
            }

            // 刷新buffer
            if (pos >= count) {
                if (read() == -1) throw new IOException(sm.getString("requestStream.readline.error"));
                pos = 0;
            }

            // 空格判断
            if (buf[pos] == SP) space = true;
            else if ((buf[pos] == CR) || (buf[pos] == LF)) {
                // 如果下一个字符是换行符或者回车符，说明第一行没有协议头了，也许是 HTTP/0.9 的请求头
                space = true;
                eol = true;
            }

            requestLine.uri[readCount++] = ((char) buf[pos++]);
        }

        requestLine.uriEnd = readCount - 1;

        // 填充protocol

        maxRead = requestLine.protocol.length;
        readCount = 0;

        while (!eol) {
            // 扩容
            if (readCount >= maxRead) {
                requestLine.protocol = extendArray(requestLine.protocol, maxRead, maxRead << 2,
                                                   HttpRequestLine.MAX_PROTOCOL_SIZE);
                maxRead = requestLine.protocol.length;
            }

            // 刷新buffer
            if (pos >= count) {
                if (read() == -1) throw new IOException(sm.getString("requestStream.readline.error"));
                pos = 0;
            }

            if (buf[pos] == LF) eol = true;
            else requestLine.protocol[readCount++] = (char) buf[pos];

            // 读到回车符直接跳过 准备读取下一个换行符
            // 可以看出这种解析方式支持 CRLF换行，也支持LF换行
            if (buf[pos] != CR) pos++;
        }

        requestLine.protocolEnd = readCount - 1;
    }

    private char[] extendArray(char[] sourceArray, int sourceLength,
                               int newSize, int limitSize) throws IOException {
        if (newSize <= limitSize) {
            char[] newBuffer = new char[newSize];
            System.arraycopy(sourceArray, 0, newBuffer, 0, sourceLength);
            return newBuffer;
        } else {
            throw new IOException(sm.getString("requestStream.readline.toolong"));
        }
    }

    public void readHeader(HttpHeader header) throws IOException {

    }
}
