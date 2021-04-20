package com.github.mrag.demo.spring.e_basepackageclass.config;

import com.github.mrag.demo.spring.e_basepackageclass.bean.DemoService;
import com.github.mrag.demo.spring.e_basepackageclass.component.DemoComponent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {DemoService.class, DemoComponent.class})
public class BasePackageClassConfiguration {

}
