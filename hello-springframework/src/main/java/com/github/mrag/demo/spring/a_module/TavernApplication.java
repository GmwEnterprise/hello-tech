package com.github.mrag.demo.spring.a_module;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class TavernApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.registerBean("TavernConfig", TavernConfiguration.class);
        ctx.refresh();
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
        ctx.close();
    }
}
