package org.example.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Consumer;

public class TestParameterizedType {

    @Test
    public void test() {
        Consumer<Class<?>> consumer = domainType -> {
            Field[] declaredFields = domainType.getDeclaredFields();
            for (Field field : declaredFields) {
                if (Iterable.class.isAssignableFrom(field.getType())) {
                    ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                    Type typeArgument = genericType.getActualTypeArguments()[0];
                    if (!(typeArgument instanceof Class)) {
                        System.out.println("another type:");
                    }
                    System.out.println(typeArgument);
                }
            }
        };

        consumer.accept(UserDomain.class);
        /*
         * output:
         * class java.lang.String
         */
    }
}

class UserDomain {
    List<String> phones;
}