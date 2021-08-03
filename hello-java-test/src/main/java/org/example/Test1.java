package org.example;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Test1 {

    public static void main(String[] args) throws Exception {

        // System.out.println(Order.class.getGeneric);

        Field orderItemsField = Order.class.getDeclaredField("orderItems");
        String.class.getDeclaredMethod("isEmpty").getTypeParameters();
        Type genericType = orderItemsField.getGenericType();
        System.out.println(genericType);
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            System.out.println(parameterizedType.getOwnerType());
            System.out.println(Arrays.toString(parameterizedType.getActualTypeArguments()));
            System.out.println(parameterizedType.getRawType());
        }
        System.out.println();

        System.out.println(Order.class.getDeclaringClass());
        System.out.println(Order.StaticOrderInner.class.getDeclaringClass());
        System.out.println(Order.StaticOrderInner.class.getEnclosingClass());
        Order anonymousOrder = new Order() {{
            System.out.printf("匿名内部类：%s%n", getClass());
        }};
        System.out.println(anonymousOrder.getClass());
        System.out.println(anonymousOrder.getClass().getDeclaringClass());
        System.out.println(anonymousOrder.getClass().getEnclosingClass());


        new Outer();

        TypeVariable<Class<Map>>[] typeParameters = Map.class.getTypeParameters();
        System.out.println(Arrays.toString(typeParameters));
    }
}

class Outer {
    Runnable task1 = () -> {};
    Runnable task2 = new Runnable() {
        @Override
        public void run() {
        }
    };

    Outer() {
        System.out.println(task1.getClass().getEnclosingClass());
        System.out.println(Arrays.toString(task1.getClass().getInterfaces()));

        System.out.println(task2.getClass().getEnclosingClass());
        System.out.println(Arrays.toString(task2.getClass().getInterfaces()));
    }
}

class Order {
    List<String> orderItems;

    public static class StaticOrderInner {
    }
}

class T {
    List<Order> list = new ArrayList<>();

    public <R extends Order> T(R c) {
        list.add(c);

        // Wildcard type '? super org.example.Order' cannot be instantiated directly
        // List<? super Order> fa = new ArrayList<? super Order>();
    }
}