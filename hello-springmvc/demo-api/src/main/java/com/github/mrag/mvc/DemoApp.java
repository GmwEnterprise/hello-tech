package com.github.mrag.mvc;

import com.github.mrag.mvc.common.CommonFilter;
import com.github.mrag.mvc.common.CommonInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Gmw
 */
@RestController
@SpringBootApplication
public class DemoApp {

    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }

    @GetMapping
    String sayHello() {
        return "Hello SpringMVC !\n";
    }

    @PostMapping(value = "/", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    Map<String, Object> postHello() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("timestamp", System.currentTimeMillis());
        map.put("message", "OK");
        return map;
    }

    @Configuration
    @EnableWebMvc
    static class WebConf implements WebMvcConfigurer {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new CommonInterceptor());
        }

        @Bean
        FilterRegistrationBean<CommonFilter> createCommonFilter() {
            FilterRegistrationBean<CommonFilter> filter = new FilterRegistrationBean<>();
            filter.setFilter(new CommonFilter());
            filter.setOrder(1);
            filter.addUrlPatterns("/*");
            return filter;
        }
    }
}
