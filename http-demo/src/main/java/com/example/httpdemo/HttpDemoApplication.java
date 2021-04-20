package com.example.httpdemo;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HttpDemoApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(HttpDemoApplication.class, args);
    }

    @RequestMapping("/paramIn")
    public ObjectNode test(@RequestBody ObjectNode body) {
        ObjectNode root = new ObjectNode(JsonNodeFactory.instance);
        root.set("requestBody", body);
        root.put("timestamp", System.currentTimeMillis());
        return root;
    }
}
