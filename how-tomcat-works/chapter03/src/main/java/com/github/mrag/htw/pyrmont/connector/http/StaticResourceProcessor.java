package com.github.mrag.htw.pyrmont.connector.http;

import java.io.IOException;

public class StaticResourceProcessor {
    public void process(HttpRequest request, HttpResponse response) {
        try {
            // 直接调用response的静态资源处理方法
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
