package com.github.mrag.htw.pyrmont.connector.http;

/**
 * 一个请求头键值对的封装
 */
final class HttpHeader {

    public static final int INITIAL_NAME_SIZE = 32;
    public static final int INITIAL_VALUE_SIZE = 64;
    public static final int MAX_NAME_SIZE = 128;
    public static final int MAX_VALUE_SIZE = 4096;

    public char[] name;
    public int nameEnd;
    public char[] value;
    public int valueEnd;

    private int hashCode = 0;

    public HttpHeader(char[] name, int nameEnd, char[] value, int valueEnd) {
        this.name = name;
        this.nameEnd = nameEnd;
        this.value = value;
        this.valueEnd = valueEnd;
    }

    // 初始化成员，赋初值
    public HttpHeader() {
        this(new char[INITIAL_NAME_SIZE], 0, new char[INITIAL_VALUE_SIZE], 0);
    }

    // 初始化成员，直接提供值
    // name必须为小写
    public HttpHeader(String name, String value) {
        this.name = name.toLowerCase().toCharArray();
        this.nameEnd = name.length();
        this.value = value.toCharArray();
        this.valueEnd = value.length();
    }

    public void recycle() {
        nameEnd = 0;
        valueEnd = 0;
        hashCode = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof String) {
            return equals(((String) obj).toLowerCase());
        } else if (obj instanceof HttpHeader) {
            return headerEquals(((HttpHeader) obj));
        }
        return false;
    }

    // 比较name
    public boolean equals(char[] buf, int end) {
        if (end != nameEnd)
            return false;
        for (int i = 0; i < end; i++) {
            if (buf[i] != name[i])
                return false;
        }
        return true;
    }

    public boolean equals(char[] buf) {
        return equals(buf, buf.length);
    }

    public boolean equals(String str) {
        return equals(str.toCharArray(), str.length());
    }

    // 比较value
    public boolean valueEquals(char[] buf, int end) {
        if (end != valueEnd)
            return false;
        for (int i = 0; i < end; i++) {
            if (buf[i] != value[i])
                return false;
        }
        return true;
    }

    public boolean valueEquals(char[] buf) {
        return valueEquals(buf, buf.length);
    }

    public boolean valueEquals(String str) {
        return valueEquals(str.toCharArray(), str.length());
    }

    public boolean valueIncludes(char[] buf, int end) {
        int limit = valueEnd - end;
        for (int i = 0; i <= limit; i++) {
            if (buf[0] != value[i]) continue;
            boolean flag = true;
            for (int j = 1; j < end; j++) {
                if (value[i + j] != buf[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) return true;
        }
        return false;
    }

    public boolean valueIncludes(String str) {
        return valueIncludes(str.toCharArray(), str.length());
    }

    public boolean headerEquals(HttpHeader header) {
        return header != null
                && equals(header.name, header.nameEnd)
                && valueEquals(header.value, header.valueEnd);
    }

    @Override
    public int hashCode() {
        int h = hashCode;
        if (h == 0) {
            int off = 0;
            char[] val = name;
            int len = nameEnd;
            for (int i = 0; i < len; i++)
                h = 31 * h + val[off++];
            hashCode = h;
        }
        return h;
    }
}
