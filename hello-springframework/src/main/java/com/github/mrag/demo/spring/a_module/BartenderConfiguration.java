package com.github.mrag.demo.spring.a_module;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BartenderConfiguration {

    @Bean
    public Bartender zhangXiaoSan() {
        return new Bartender("张小三");
    }

    @Bean
    public Bartender zhangDaSan() {
        return new Bartender("张大三");
    }
}

