package com.github.mrag.helloim.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class HelloImController {

    @GetMapping
    public String sayHello() {
        return "Hello !";
    }
}
