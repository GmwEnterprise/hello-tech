package com.github.mrag.demo.spring.b_profile.anno;

import com.github.mrag.demo.spring.b_profile.selector.BarImportSelector;
import com.github.mrag.demo.spring.b_profile.component.Boss;
import com.github.mrag.demo.spring.b_profile.registrar.WaiterRegistrar;
import com.github.mrag.demo.spring.b_profile.config.BartenderConfiguration;
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
