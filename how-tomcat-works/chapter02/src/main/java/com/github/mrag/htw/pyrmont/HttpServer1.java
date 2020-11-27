package com.github.mrag.htw.pyrmont;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现一个最简单的servlet容器
 */
public class HttpServer1 {

    private static final String SHUTDOWN_COMMAND = "/shutdown";

    private boolean shutdown = false;

    public void await() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(8080, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!shutdown) {
            InputStream input = null;
            OutputStream output = null;
            Socket socket = null;
            try {
                socket = ss.accept();
                input  = socket.getInputStream();
                output = socket.getOutputStream();

                Request request = new Request(input);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);

                if (request.getUri().startsWith("/servlet")) {
                    // 这个请求想要访问servlet
                    ServletProcessor1 processor = new ServletProcessor1();
                    processor.process(request, response);
                } else {
                    // 这个请求想要访问静态资源
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }

                socket.close();
                shutdown = SHUTDOWN_COMMAND.equals(request.getUri());
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) {
        new HttpServer1().await();
    }
}
