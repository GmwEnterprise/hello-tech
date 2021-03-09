package com.github.mrag.demo.spring.test;

import org.springframework.stereotype.Component;

@Component
public class Shape {
    private String shapeName;

    public String getShapeName() {
        return shapeName;
    }

    public Shape setShapeName(String shapeName) {
        this.shapeName = shapeName;
        return this;
    }
}
