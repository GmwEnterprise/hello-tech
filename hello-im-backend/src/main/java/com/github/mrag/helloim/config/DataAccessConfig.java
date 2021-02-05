package com.github.mrag.helloim.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.github.mrag.helloim.dao")
public class DataAccessConfig {
}
