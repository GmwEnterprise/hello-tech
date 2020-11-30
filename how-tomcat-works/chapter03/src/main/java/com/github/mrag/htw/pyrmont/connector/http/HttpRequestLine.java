package com.github.mrag.htw.pyrmont.connector.http;

final class HttpRequestLine {
    // 初始 method 长度
    public static final int INITIAL_METHOD_SIZE = 8;
    // 初始 URI 长度
    public static final int INITIAL_URI_SIZE = 64;
    // 初始协议长度
    public static final int INITIAL_PROTOCOL_SIZE = 8;
    // 最大 method 长度
    public static final int MAX_METHOD_SIZE = 1024;
    // 最大 URI 长度
    public static final int MAX_URI_SIZE = 32768;
    // 最大协议长度
    public static final int MAX_PROTOCOL_SIZE = 1024;

    public char[] method;
    public int methodEnd;
    public char[] uri;
    public int uriEnd;
    public char[] protocol;
    public int protocolEnd;

    public HttpRequestLine(char[] method, int methodEnd,
                           char[] uri, int uriEnd,
                           char[] protocol, int protocolEnd) {
        this.method      = method;
        this.methodEnd   = methodEnd;
        this.uri         = uri;
        this.uriEnd      = uriEnd;
        this.protocol    = protocol;
        this.protocolEnd = protocolEnd;
    }

    public HttpRequestLine() {
        this(new char[INITIAL_METHOD_SIZE], 0,
             new char[INITIAL_URI_SIZE], 0,
             new char[INITIAL_PROTOCOL_SIZE], 0);
    }

    // buffer 可重用
    public void recycle() {
        methodEnd   = 0;
        uriEnd      = 0;
        protocolEnd = 0;
    }

    // 测试 URI 中是否包含给定字符数组
    public int indexOf(char[] buf, int end) {

    }

    public static void main(String[] args) {
        HttpRequestLine line = new HttpRequestLine();
        line.uri    = "Hello world !".toCharArray();
        line.uriEnd = line.uri.length;

        char[] buf = "lo w".toCharArray();
        int end = line.indexOf(buf, buf.length);

        if (end != -1) {
            int start = end - buf.length;
            for (int idx = 0; idx < line.uriEnd; idx++) {
                if (idx == start) {
                    System.out.print("__[");
                }
                System.out.print(line.uri[idx]);
                if (idx == end - 1) {
                    System.out.print("]__");
                }
            }
        }
    }
}
