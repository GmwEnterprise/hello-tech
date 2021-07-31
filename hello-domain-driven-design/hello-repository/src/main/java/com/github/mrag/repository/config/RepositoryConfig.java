package com.github.mrag.repository.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.github.mrag.repository.dao")
public class RepositoryConfig {
}
