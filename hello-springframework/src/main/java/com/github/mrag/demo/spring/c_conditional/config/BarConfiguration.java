package com.github.mrag.demo.spring.c_conditional.config;

import com.github.mrag.demo.spring.c_conditional.component.Bar;
import com.github.mrag.demo.spring.c_conditional.condition.ExistBossCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BarConfiguration {

    @Bean
    @Conditional(ExistBossCondition.class)
    public Bar bbbar() {
        return new Bar();
    }
}
