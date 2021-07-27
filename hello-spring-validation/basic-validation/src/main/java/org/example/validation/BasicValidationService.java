package org.example.validation;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class BasicValidationService {

    public void sayHello(@NonNull String name) {
        System.out.printf("Hello, %s%n", name);
    }
}
