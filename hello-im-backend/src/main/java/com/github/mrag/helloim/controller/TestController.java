package com.github.mrag.helloim.controller;

import com.github.mrag.helloim.common.enums.MessageStatus;
import com.github.mrag.helloim.domain.ImUserMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/enums")
    public ImUserMessage testEnums(@RequestParam(required = false) MessageStatus messageStatus,
                                   @RequestBody(required = false) ImUserMessage imUserMessage) {
        ImUserMessage result = new ImUserMessage();
        result.setMessageStatus(MessageStatus.AlreadyReceived);
        return result;
    }

    @GetMapping("/enums1")
    public MessageStatus testEnums1(@RequestBody(required = false) ImUserMessage body) {
        System.out.println(body.getMessageStatus());
        return MessageStatus.AlreadyReceived;
    }
}
