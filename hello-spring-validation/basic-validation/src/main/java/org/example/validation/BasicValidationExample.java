package org.example.validation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BasicValidationExample {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BasicValidationConfig.class);
        context.refresh();

        try {
            BasicValidationService bvs = context.getBean(BasicValidationService.class);
            bvs.sayHello("Mrag");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        context.close();
    }
}
