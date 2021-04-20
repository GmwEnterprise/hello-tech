package com.github.mrag.demo.spring.f_typefilter;

import com.github.mrag.demo.spring.f_typefilter.config.TypeFilterConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class TypeFilterApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TypeFilterConfiguration.class);
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
