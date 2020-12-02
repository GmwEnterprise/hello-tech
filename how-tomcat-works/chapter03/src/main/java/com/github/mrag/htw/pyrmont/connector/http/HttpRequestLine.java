package com.github.mrag.htw.pyrmont.connector.http;

/**
 * 支持重复读取的请求行封装
 */
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
        this.method = method;
        this.methodEnd = methodEnd;
        this.uri = uri;
        this.uriEnd = uriEnd;
        this.protocol = protocol;
        this.protocolEnd = protocolEnd;
    }

    public HttpRequestLine() {
        this(new char[INITIAL_METHOD_SIZE], 0,
             new char[INITIAL_URI_SIZE], 0,
             new char[INITIAL_PROTOCOL_SIZE], 0);
    }

    // buffer 可重用
    public void recycle() {
        methodEnd = 0;
        uriEnd = 0;
        protocolEnd = 0;
    }

    // 测试 URI 中是否包含给定字符数组
    // 若匹配，则返回子串在uri中的第一个下标；否则返回-1
    public int indexOfUri(char[] buf, int end) {
        int pos = 0;
        int limit = uriEnd - end + 1;
        while (pos < limit) {
            boolean matches = true;
            for (int i = 0; i < end; i++) {
                if (uri[pos + i] != buf[i]) {
                    pos++;
                    matches = false;
                    break;
                }
            }
            if (matches) {
                return pos;
            }
        }
        return -1;
    }

    public int indexOfUri(char[] buf) {
        return indexOfUri(buf, buf.length);
    }

    public int indexOfUri(String str) {
        return indexOfUri(str.toCharArray(),
                          str.length());
    }
}
