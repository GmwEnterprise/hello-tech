package com.github.mrag.mvc;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jackson")
public class TestController {

    @PostMapping(value = "/test1", consumes = MediaType.APPLICATION_XML_VALUE)
    Resp test1(@RequestBody(required = false) Data data) {
        if (data != null) {
            System.out.println(data);
        } else {
            data = new Data();
        }
        return Resp.ok(data);
    }
}
