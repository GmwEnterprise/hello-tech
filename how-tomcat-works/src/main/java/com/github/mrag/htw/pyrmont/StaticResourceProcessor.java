package com.github.mrag.htw.pyrmont;

import java.io.IOException;

public class StaticResourceProcessor {
    public void process(Request request, Response response) {
        try {
            // 直接调用response的静态资源处理方法
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
