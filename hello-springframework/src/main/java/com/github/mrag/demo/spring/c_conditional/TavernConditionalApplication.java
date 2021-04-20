package com.github.mrag.demo.spring.c_conditional;

import com.github.mrag.demo.spring.c_conditional.config.TavernConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

// tavern：酒馆
public class TavernConditionalApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.registerBean("TavernConfig", TavernConfiguration.class);
        ctx.refresh();
        Stream.of(ctx.getBeanDefinitionNames()).forEach(System.out::println);
        ctx.close();
    }
}
