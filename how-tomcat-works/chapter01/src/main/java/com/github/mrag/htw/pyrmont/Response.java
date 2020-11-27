package com.github.mrag.htw.pyrmont;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {
    private static final int BUFFER_SIZE = 1024;
    private Request request;
    private final OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {
            // 直接通过URI去webroot文件夹查找有没有对应路径的静态资源
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if (file.exists()) {
                // 添加响应头
                output.write(("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n").getBytes());
                // 添加响应内容
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    output.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            } else {
                // 构建一个简单的404错误响应报文
                String errorMessage = "HTTP/1.1 404 File Not Found\r\nContent-Type: text/html\r\n" +
                        "Content-Length: 23\r\n\r\n<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }
}
