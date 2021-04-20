package com.github.mrag.demo.spring.c_conditional.anno;

import com.github.mrag.demo.spring.c_conditional.component.Boss;
import com.github.mrag.demo.spring.c_conditional.config.BartenderConfiguration;
import com.github.mrag.demo.spring.c_conditional.registrar.WaiterRegistrar;
import com.github.mrag.demo.spring.c_conditional.selector.BarImportSelector;
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
