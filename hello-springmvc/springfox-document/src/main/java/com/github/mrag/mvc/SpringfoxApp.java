package com.github.mrag.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Gmw
 */
@SpringBootApplication
public class SpringfoxApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringfoxApp.class, args);
        System.out.println("MAIN END");
    }
}
