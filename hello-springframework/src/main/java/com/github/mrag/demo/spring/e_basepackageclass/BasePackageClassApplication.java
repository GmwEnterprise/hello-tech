package com.github.mrag.demo.spring.e_basepackageclass;

import com.github.mrag.demo.spring.e_basepackageclass.config.BasePackageClassConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class BasePackageClassApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(BasePackageClassConfiguration.class);
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
