package com.github.mrag.htw.pyrmont.connector.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpConnector implements Runnable {
    private boolean stopped = false;

    public String getSchema() {
        return "http";
    }

    public void start() {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(this);
    }

    @Override
    public void run() {
        ServerSocket ss = null;
        int port = 8080;
        try {
            ss = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!stopped) {
            // 循环接收 tcp 请求
            Socket socket;
            try {
                // 等待http请求
                socket = ss.accept();
            } catch (IOException e) {
                System.out.println("连接接收失败：" + e);
                continue;
            }
            // 为每个请求创建一个HttpProcessor
            HttpProcessor processor = new HttpProcessor(this);
            // 调用处理方法
            processor.process(socket);
        }
    }
}
