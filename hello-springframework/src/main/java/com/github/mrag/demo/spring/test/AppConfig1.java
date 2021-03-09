package com.github.mrag.demo.spring.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig1 {

    @Bean
    public Color namedColor() {
        return new Color();
    }
}
