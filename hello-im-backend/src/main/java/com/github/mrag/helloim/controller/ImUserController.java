package com.github.mrag.helloim.controller;

import com.github.mrag.helloim.common.*;
import com.github.mrag.helloim.domain.ImUser;
import com.github.mrag.helloim.dto.ImUserDto;
import com.github.mrag.helloim.service.ImUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/im-user")
public class ImUserController {
    @Resource
    ImUserService imUserService;

    @Permission
    @GetMapping("/about-me")
    public HttpResponse findUserById(@RequestAttribute Integer userId) {
        ImUser me = imUserService.findUserById(userId);
        return HttpResponse.ok(me);
    }

    @Permission
    @PostMapping("/token-validation")
    public HttpResponse tokenValidation() {
        return HttpResponse.ok(null);
    }

    @PostMapping("/parameter-validation")
    public HttpResponse parameterValidation(@RequestBody @Valid ImUserDto userDto) {
        return HttpResponse.ok(userDto);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<HttpResponse> signIn(@RequestParam @Pattern(regexp = Consts.RegExp.USERNAME) String username,
                                               @RequestParam @Pattern(regexp = Consts.RegExp.PASSWORD) String password) {
        String tokenStr = imUserService.signInByUsername(username, password);
        return ResponseEntity.ok()
                             .header(HttpToken.PERMISSION_HEADER_NAME, tokenStr)
                             .body(HttpResponse.ok(null));
    }

    @PostMapping("/sign-on")
    public ResponseEntity<HttpResponse> signOn(@RequestBody ImUser user) {
        Asserts.registryMessageNotEmpty(user);
        String tokenStr = imUserService.signOn(user);
        return ResponseEntity.ok()
                             .header(HttpToken.PERMISSION_HEADER_NAME, tokenStr)
                             .body(HttpResponse.ok(null));
    }
}
