package com.github.mrag.htw.pyrmont.connector.http;

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
    // 合法字符长度
    private int count;
    // buf中的当前位置
    private int pos;

    public SocketInputStream(InputStream input, byte[] buf) {
        this.input = input;
        this.buf   = buf;
    }

    public void readRequestLine() {

    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
