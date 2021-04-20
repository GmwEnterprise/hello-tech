package com.github.mrag.mvc.web;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.mrag.mvc.vo.ApiResult;
import com.github.mrag.mvc.vo.Demo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author Gmw
 */
@Api("Api文档Demo控制器")
@RestController
@RequestMapping("/demo")
public class DemoController {

    @PostMapping("/output")
    ApiResult<Object> output(@RequestBody ObjectNode body) {
        ObjectNode node = new ObjectNode(JsonNodeFactory.instance);
        node.set("reqBody", body);
        return ApiResult.ok(node);
    }

    @GetMapping("/path/{Int}/{Long}/{Str}")
    ApiResult<Demo> pathVariable(@PathVariable("Int") Integer intValue,
                                 @PathVariable("Long") Integer longValue,
                                 @PathVariable("Str") Integer strValue) {
        Demo demo = new Demo();
        return ApiResult.ok(demo);
    }
}
