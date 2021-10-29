package com.example.hello.maven;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainClass {

    public static void main(String[] args) throws IOException {
        System.out.printf("[time: %s] %s%n",
                          System.currentTimeMillis(),
                          StringUtils.deleteWhitespace(" Hello World !   "));
        InputStream input = Thread.currentThread()
                                  .getContextClassLoader()
                                  .getResourceAsStream("schema.json");
        String content = Utils.inputStreamToString(input, StandardCharsets.UTF_8);
        JSONObject json = JSON.parseObject(content);
        System.out.println(json.getString("author"));
    }
}
