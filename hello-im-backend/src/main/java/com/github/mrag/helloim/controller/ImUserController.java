package com.github.mrag.helloim.controller;

import com.github.mrag.helloim.dao.ImUserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class HelloImController {

    @Resource
    ImUserMapper imUserMapper;

    @GetMapping
    public String sayHello() {
        return "Hello !" + imUserMapper.selectAll();
    }
}
