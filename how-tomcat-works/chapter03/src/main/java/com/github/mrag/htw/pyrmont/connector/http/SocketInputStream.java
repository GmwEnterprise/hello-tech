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

    public SocketInputStream(InputStream input, int bufSize) {
        this(input, new byte[bufSize]);
    }

    // 读取一个字符，且 pos 指针后移一位
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

    // 填充一组请求头信息到HttpHeader的一个实例中
    // 应该会调用多次，用以填充一个HttpHeader列表
    public void readHeader(HttpHeader header) throws IOException {
        // 重置header
        if (header.nameEnd != 0) {
            header.recycle();
        }

        // 检测是否是换行符
        // 如果是，就表明请求头读取结束
        int ch = read();
        if (ch == CR || ch == LF) {
            if (ch == CR) read();
            header.nameEnd = 0;
            header.valueEnd = 0;
            // 没有请求头 直接结束
            // 此时pos指针已经跳过空行，指向请求体的第一个字节
            return;
        } else {
            // 回退
            pos--;
        }

        // 读name

        int readCount = 0;
        boolean colon = false;

        while (!colon) {
            // 检测扩容
            if (readCount >= header.name.length) {
                int newSize = header.name.length << 2;
                header.name = extendArray(header.name, header.name.length,
                                          newSize, HttpHeader.MAX_NAME_SIZE);
            }

            // buffer缓存刷新
            if (pos >= count) {
                if (read() == -1) throw new IOException(sm.getString("requestStream.readline.error"));
                pos = 0;
            }

            // 检测结束符
            if (buf[pos] == COLON) {
                colon = true;
            }

            // 读取这个字符
            char val = (char) buf[pos];
            // 大小写转换
            if (val >= 'A' && val <= 'Z') {
                val -= LC_OFFSET;
            }
            header.name[readCount++] = val;
            pos++;
        }
        header.nameEnd = readCount - 1;

        // 读value，value可能为多行

        readCount = 0;
        // int crPos = -2;
        boolean eol = false;

        // boolean validLine = true;

        // while (validLine) {

        // 跳过空格、制表符
        boolean space = true;
        while (space) {
            if (pos > count) {
                if (read() == -1) throw new IOException(sm.getString("requestStream.readline.error"));
                pos = 0;
            }
            if (buf[pos] == SP || buf[pos] == HT) {
                pos++;
            } else {
                space = false;
            }
        }

        // 读取当前行剩余字符
        while (!eol) {
            // 扩容
            if (readCount >= header.value.length) {
                header.value = extendArray(header.value, header.value.length,
                                           header.value.length << 2, HttpHeader.MAX_VALUE_SIZE);
            }

            // buf刷新
            if (pos > count) {
                if (read() == -1) throw new IOException(sm.getString("requestStream.readline.error"));
                pos = 0;
            }

            if (buf[pos] == CR) {
                // 跳过回车符，下一个就是换行符 LF
            } else if (buf[pos] == LF) {
                eol = true;
            } else {
                header.value[readCount++] = (char) (buf[pos] & 0xff);
            }
            pos++;
        }

        // 源码中这里还有一个读取下一行第一个字符判断是不是空白符的操作
        // 这里我省略了  因为http请求现在似乎不支持换行符后还有value续接
        // }

        header.valueEnd = readCount;
    }

    @Override
    public int available() throws IOException {
        return count - pos + input.available();
    }
}
