package com.github.mrag.htw.pyrmont.connector.http;

import org.apache.naming.StringManager;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class SocketInputStream extends InputStream {
    private static final byte CR        = (byte) '\r';
    private static final byte LF        = (byte) '\n';
    private static final byte SP        = (byte) ' ';
    private static final byte HT        = (byte) '\t';
    private static final byte COLON     = (byte) ':';
    private static final int  LC_OFFSET = 'A' - 'a';

    // 真实持有的输入流
    private final InputStream input;
    // 内部缓冲器
    private final byte[]      buf;
    // 合法字符长度
    private       int         count;
    // buf中的当前位置
    private       int         pos;

    private final StringManager stringManager = StringManager
            .getManager("com.github.mrag.htw.pyrmont.connector.http");

    public SocketInputStream(InputStream input, byte[] buf) {
        this.input = input;
        this.buf   = buf;
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
        pos   = 0; // 位置重置
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
            throw new EOFException(stringManager.getString("requestStream.readline.error"));
        }

        pos--; // 回退一格, 因为do while必然多走一格

        // 读取 methodName，写入到requestLine.method[]
        int     maxRead   = requestLine.method.length;
        int     readStart = pos;
        int     readCount = 0;
        boolean space   = false;
        while (!space) {
            // buffer扩容
            if (readCount >= buf.length) {

            }
        }
    }
}
