package com.github.mrag.demo.spring.f_typefilter.config;

import com.github.mrag.demo.spring.f_typefilter.anno.Animal;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.github.mrag.demo.spring.f_typefilter",
               includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
                                                      value = Animal.class))
public class TypeFilterConfiguration {
}
