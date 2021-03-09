package com.github.mrag.demo.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class QuickstartListenerApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.github.mrag.demo.spring.event");
        System.out.println("准备初始化IOC容器。");
        context.refresh();
        System.out.println("IOC容器初始化完成。");
        context.close();
        System.out.println("IOC容器关闭。");
    }
}
