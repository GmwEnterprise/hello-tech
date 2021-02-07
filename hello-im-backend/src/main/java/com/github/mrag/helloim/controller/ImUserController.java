package com.github.mrag.helloim.controller;

import com.github.mrag.helloim.common.HttpResponse;
import com.github.mrag.helloim.common.Permission;
import com.github.mrag.helloim.domain.ImUser;
import com.github.mrag.helloim.security.HttpToken;
import com.github.mrag.helloim.security.HttpTokenUtils;
import com.github.mrag.helloim.service.ImUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/im-user")
public class ImUserController {
    @Resource
    ImUserService imUserService;
    @Resource
    HttpTokenUtils httpTokenUtils;

    @GetMapping("/about-me")
    @Permission
    public HttpResponse findUserById(@RequestAttribute Integer userId) {
        ImUser me = imUserService.findUserById(userId);
        return HttpResponse.ok(me);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<HttpResponse> signIn(@RequestParam String username,
                                               @RequestParam String password) {
        String tokenStr = imUserService.signInByUsername(username, password);
        return ResponseEntity.ok().header(HttpToken.PERMISSION_HEADER_NAME, tokenStr).build();
    }
}
