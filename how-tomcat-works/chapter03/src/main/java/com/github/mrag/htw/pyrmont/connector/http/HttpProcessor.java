package com.github.mrag.htw.pyrmont.connector.http;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class HttpProcessor {
    private HttpRequest request;
    private HttpResponse response;

    private HttpRequestLine requestLine = new HttpRequestLine();

    public HttpProcessor(HttpConnector httpConnector) {
    }

    public void process(Socket socket) {

        SocketInputStream input = null;
        OutputStream output = null;
        try {
            input = new SocketInputStream(socket.getInputStream(), 2048);
            output = socket.getOutputStream();

            // 创建request对象
            request = new HttpRequest();

            // 创建response对象
            response = new HttpResponse();
            response.setRequest(request);
            response.setHeader("Server", "Pyrmont Servlet Container");

            parseRequest(input, output);
            parseHeaders(input);

            if (request.getRequestURI().startsWith("/servlet/")) {
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request, response);
            }

            socket.close();

        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }

    }

    private void parseRequest(SocketInputStream input, OutputStream output) throws IOException, ServletException {
        input.readRequestLine(requestLine);

        String method = new String(requestLine.method, 0, requestLine.methodEnd);
        String uri = null; // 需要解析请求参数与URI分离
        String protocol = new String(requestLine.protocol, 0, requestLine.protocolEnd);

        // 验证method与URI
        if (method.length() < 1) throw new ServletException("Missing HTTP request method");
        if (requestLine.uriEnd < 1) throw new ServletException("Missing HTTP request URI");

        // 解析请求参数
        int question = requestLine.indexOfUri("?");
        if (question == -1) {
            // 没有请求参数
            request.setQueryString(null);
            uri = new String(requestLine.uri, 0, requestLine.uriEnd);
        } else {
            request.setQueryString(new String(
                    requestLine.uri, question + 1, requestLine.uriEnd - question - 1));
            uri = new String(requestLine.uri, 0, question);
        }

        // 获取http请求绝对URI
        if (!uri.startsWith("/")) {
            int pos = uri.indexOf("://");
            if (pos != -1) {
                // e.g.   http://localhost:8080/servlet
                pos = uri.indexOf('/', pos + 3);
                if (pos == -1) {
                    uri = "";
                } else {
                    uri = uri.substring(pos);
                }
            }
        }

        // 解析session ID
        String match = ";jsessionid=";
        int semicolon = uri.indexOf(match);
        if (semicolon != -1) {
            // jsessionid的值
            String rest = uri.substring(semicolon + match.length());
            int semicolon2 = rest.indexOf(';');
            if (semicolon2 != -1) {
                // TODO 了解关于jsessionid的规范
            }
        }
    }

    private void parseHeaders(SocketInputStream input) {

    }
}
