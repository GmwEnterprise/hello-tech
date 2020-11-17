package example.enablexxx;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({Red.class,
        ColorRegistrarConfiguration.class,
        ColorImportSelector.class,
        ColorImportBeanDefinitionRegistrar.class})
public @interface EnableColor {

}
