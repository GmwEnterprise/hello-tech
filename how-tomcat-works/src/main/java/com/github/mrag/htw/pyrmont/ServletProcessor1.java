package com.github.mrag.htw.pyrmont;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class ServletProcessor1 {
    public void process(Request request, Response response) {
        String uri = request.getUri();
        // 从URI中获取需要访问的servlet类名
        String serviceName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;
        try {
            // 初始化ClassLoader, repo = `file:${绝对路径}\how-tomcat-works\webroot\`
            String repository = new URL(
                    "file", null,
                    // URL如果以分隔符'\'结尾，则表示指向一个目录，URLClassLoader则会去目录中查询对应的class；
                    // 否则就只能表示一个jar文件
                    new File(Constants.WEB_ROOT).getCanonicalPath() + File.separator).toString();
            // (null, repository, null)的写法会使编译器无法确定到底使用哪一个构造函数
            // 书中写法为提前声明一个URLStreamHandler的引用，但不赋值
            loader = new URLClassLoader(Tools.array(new URL(null, repository, ((URLStreamHandler) null))));
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        Class<?> myClass = null;
        try {
            myClass = loader.loadClass(serviceName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        Servlet servlet = null;
        try {
            servlet = ((Servlet) myClass.getConstructor().newInstance());
        } catch (Throwable e) {
            System.out.println(e.toString());
        }
    }

    // 测试
    public static void main(String[] args) throws Exception {
        Request request = new Request(null);
        Field uriField = request.getClass().getDeclaredField("uri");
        uriField.setAccessible(true);
        uriField.set(request, "PrimitiveServlet");

        new ServletProcessor1().process(request, null);
    }
}
