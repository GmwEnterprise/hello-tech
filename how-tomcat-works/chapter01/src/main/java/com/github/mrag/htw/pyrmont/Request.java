package com.github.mrag.htw.pyrmont;

import java.io.IOException;
import java.io.InputStream;

public class Request {
    private final InputStream input;
    private String uri;

    public Request(InputStream input) {
        // 构造函数接收一个来自socket的输入流，用于解析请求报文
        this.input = input;
    }

    // 解析
    public void parse() {
        StringBuilder request = new StringBuilder(2048);
        int i;
        byte[] buf = new byte[2048];
        try {
            i = input.read(buf);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j = 0; j < i; j++) {
            // 这种转换方式适用于ascii码，支持byte直接强转为char
            char c = (char) buf[j];
            request.append(c);
        }
        System.out.println(request.toString());
        uri = parseUri(request.toString());
    }

    private String parseUri(String requestString) {
        int index1 = requestString.indexOf(" ");
        if (index1 != -1) {
            int index2 = requestString.indexOf(" ", index1 + 1);
            if (index2 > index1) {
                return requestString.substring(index1 + 1, index2);
            }
        }
        return "";
    }

    public String getUri() {
        return uri;
    }
}
