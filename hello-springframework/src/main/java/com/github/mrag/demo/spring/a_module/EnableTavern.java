package com.github.mrag.demo.spring.a_module;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({Boss.class,
         BartenderConfiguration.class,
         BarImportSelector.class,
         WaiterRegistrar.class})
public @interface EnableTavern {
}
