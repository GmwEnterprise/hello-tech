package com.github.mrag.demo.spring.c_conditional.selector;

import com.github.mrag.demo.spring.c_conditional.component.Bar;
import com.github.mrag.demo.spring.c_conditional.config.BarConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

public class BarImportSelector implements ImportSelector,
        EnvironmentAware, BeanClassLoaderAware, BeanFactoryAware, ResourceLoaderAware {

    public BarImportSelector(Environment environment,
                             BeanFactory beanFactory,
                             ClassLoader classLoader,
                             ResourceLoader resourceLoader) {
        System.out.println("constructor: " + environment);
        System.out.println("constructor: " + beanFactory);
        System.out.println("constructor: " + classLoader);
        System.out.println("constructor: " + resourceLoader);
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("selectImports execute.");
        return new String[]{Bar.class.getName(), BarConfiguration.class.getName()};
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("     import: " + classLoader);
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("     import: " + environment);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("     import: " + beanFactory);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("     import: " + resourceLoader);
    }
}
