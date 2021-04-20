package com.github.mrag.mvc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ContextAwareImpl implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (context == null) context = applicationContext;
    }

    public static <R> R bean(Class<R> type) {
        return context.getBean(type);
    }

    public static <R> R bean(Class<R> beanType, String beanName) {
        return context.getBean(beanName, beanType);
    }
}
