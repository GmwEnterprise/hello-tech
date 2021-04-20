package com.github.mrag.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Gmw
 */
@SpringBootApplication
@EnableWebMvc
public class App implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
