package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(basePackageClasses = TestSpring.class)
public class TestSpring {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestSpring.class);

        A bean = context.getBean(A.class);
        bean.speak();

        context.close();
    }
}

@Component
class A {
    private final B b;

    public A(@Lazy B b) {
        this.b = b;
    }

    void speak() {
        System.out.println("a");
    }
}

@Component
class B {
    private final A a;

    public B(A a) {
        this.a = a;
    }

    void speak() {
        System.out.println("b");
    }
}