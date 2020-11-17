package com.github.mrag.demo.spring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component("square")
public class ShapeFactory implements FactoryBean<Shape> {
    @Override
    public Shape getObject() throws Exception {
        return new Shape().setShapeName("Square");
    }

    @Override
    public Class<?> getObjectType() {
        return Shape.class;
    }
}
