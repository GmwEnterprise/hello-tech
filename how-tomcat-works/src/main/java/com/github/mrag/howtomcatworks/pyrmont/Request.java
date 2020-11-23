package com.github.mrag.howtomcatworks.pyrmont;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Request {
    private final InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

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
            char c = (char) buf[j];
            request.append(c);
        }
        System.out.print(request.toString());
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
        return null;
    }

    public String getUri() {
        return uri;
    }

    public static void main(String[] args) {
        Request request = new Request(new ByteArrayInputStream("GET /user/1 HTTP/1.1".getBytes()));
        request.parse();
        System.out.println(request.getUri());
    }
}
