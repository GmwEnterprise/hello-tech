package com.github.mrag.demo.spring.b_profile;

import com.github.mrag.demo.spring.b_profile.config.TavernConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

// tavern：酒馆
public class TavernProfileApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.registerBean("TavernConfig", TavernConfiguration.class);
        ctx.getEnvironment().setActiveProfiles("city");
        ctx.refresh();
        Stream.of(ctx.getBeanDefinitionNames()).forEach(System.out::println);
        ctx.close();
    }
}
