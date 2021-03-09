package com.github.mrag.demo.spring.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(AppConfig1.class, AppConfig2.class);
            ctx.refresh();

            Object bean = ctx.getBean("square");
            System.out.println(((Shape) bean).getShapeName());
        }
    }
}
