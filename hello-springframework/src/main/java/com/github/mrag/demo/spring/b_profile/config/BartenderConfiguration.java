package com.github.mrag.demo.spring.b_profile.config;

import com.github.mrag.demo.spring.b_profile.component.Bartender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("city")
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

