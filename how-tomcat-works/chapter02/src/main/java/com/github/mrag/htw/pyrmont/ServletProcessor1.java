package com.github.mrag.htw.pyrmont;

import com.github.mrag.htw.common.Tools;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

public class ServletProcessor1 {
    public void process(Request request, Response response) {
        String uri = request.getUri();
        String serviceName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;
        try {
            // 初始化loader
            URL webrootUrl = new File(Constants.WEB_ROOT).toURI().toURL();
            loader = URLClassLoader.newInstance(Tools.array(webrootUrl));
        } catch (IOException e) {
            System.out.printf("URL类加载器初始化失败: %s%n", e);
            System.exit(1);
        }

        Class<?> servletClass;
        try {
            servletClass = loader.loadClass(serviceName);
        } catch (Exception e) {
            System.out.printf("Servlet类加载失败: %s%n", e);
            return;
        }

        try {
            Servlet servlet = ((Servlet) servletClass.getConstructor().newInstance());
            response.getWriter().println("HTTP/1.1 200 OK\r\n");
            servlet.service(request, response);
        } catch (Throwable e) {
            System.out.println(e.toString());
        }
    }
}
