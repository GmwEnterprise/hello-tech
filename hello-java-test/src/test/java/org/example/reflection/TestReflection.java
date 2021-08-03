package org.example.reflection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestReflection<X, Y> {
    public static class TestGenericNumber<A, B, C, D> {}

    private TestGenericNumber<Integer, List<String>, ?, ? extends Map<String, ?>> generic;
    private Map<String, List<Integer>> map;
    private Map<String, ?> map2;
    private String str1;

    private void func(List<String> param) {}

    private <R, T> R func2(T t) {return null;}

    @Test
    public void testWrapClass() {
        Function<Class<?>, Boolean> f = type -> {
            if (type.isPrimitive()) {
                return true;
            }
            try {
                Field primitiveType = type.getField("TYPE");
                return ((Class<?>) primitiveType.get(null)).isPrimitive();
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return false;
            }
        };

        System.out.println(f.apply(Integer.class));
        System.out.println(f.apply(String.class));
        System.out.println(f.apply(List.class));
        System.out.println(f.apply(Number.class));
        System.out.println(f.apply(double.class));
    }

    @Test
    public void testTypeVariables() throws Exception {
        Method func2 = TestReflection.class.getDeclaredMethod("func2", Object.class);
        System.out.println(Arrays.toString(func2.getTypeParameters()));
        System.out.println(Arrays.toString(func2.getGenericParameterTypes()));
        System.out.println(func2.getGenericReturnType());
        /*
         * output:
         * [R, T]
         * [T]
         * R
         */

        System.out.println(Arrays.toString(TestReflection.class.getTypeParameters()));
        /*
         * output:
         * [X, Y]
         */
    }

    @Test
    public void testInnerClass() {
        System.out.println(Arrays.toString(TestGenericNumber.class.getDeclaredClasses()));
        System.out.println(TestGenericNumber.class.getEnclosingClass());
        /*
         * output:
         * []
         * class org.example.reflection.TestReflection
         */
    }

    @Test
    public void testGetActualTypeArguments() throws Exception {
        Type genericType = TestReflection.class.getDeclaredField("map").getGenericType();
        if (genericType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
            Class<?>[] classArray = Arrays
                    .stream(actualTypeArguments)
                    .map(type -> {
                        if (type instanceof Class) {
                            return ((Class<?>) type);
                        }
                        if (type instanceof ParameterizedType) {
                            return ((Class<?>) ((ParameterizedType) type).getRawType());
                        }
                        return null;
                    }).toArray(Class[]::new);
            System.out.println(Arrays.toString(classArray));
            /*
             * output:
             * [class java.lang.String, interface java.util.List]
             */
        }
    }

    @Test
    public void getFuncParameterTypeClass() throws Exception {
        Method func = TestReflection.class.getDeclaredMethod("func", List.class);
        Type genericParameterType = func.getGenericParameterTypes()[0];
        Assertions.assertTrue(genericParameterType instanceof ParameterizedType);
        ParameterizedType parameterizedType = (ParameterizedType) genericParameterType;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        System.out.println(Arrays.toString(actualTypeArguments));
        System.out.println(Arrays.stream(actualTypeArguments).map(Type::getClass).collect(Collectors.toList()));
        /*
         * output:
         * [class java.lang.String]
         * [class java.lang.Class]
         */
    }

    @Test
    public void getStringType() throws Exception {
        Field str1 = TestReflection.class.getDeclaredField("str1");
        Type genericType = str1.getGenericType();
        Assertions.assertTrue(genericType instanceof Class);
        System.out.println(genericType);
        /*
         * output:
         * class java.lang.String
         */
    }

    @Test
    public void getGenericTypeClass() throws Exception {
        Field field = TestReflection.class.getDeclaredField("map");
        Type genericType = field.getGenericType();
        Assertions.assertTrue(genericType instanceof ParameterizedType);
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        System.out.println(Arrays.toString(actualTypeArguments));
        System.out.println(Arrays.stream(actualTypeArguments).map(Type::getClass).collect(Collectors.toList()));
        /*
         * output:
         * [class java.lang.String, java.util.List<java.lang.Integer>]
         * [class java.lang.Class, class sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl]
         */
    }

    @Test
    public void getGenericTypeClass2() throws Exception {
        Field field = TestReflection.class.getDeclaredField("map2");
        Type genericType = field.getGenericType();
        Assertions.assertTrue(genericType instanceof ParameterizedType);
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        System.out.println(Arrays.toString(actualTypeArguments));
        System.out.println(Arrays.stream(actualTypeArguments).map(Type::getClass).collect(Collectors.toList()));
        /*
         * output:
         * [class java.lang.String, ?]
         * [class java.lang.Class, class sun.reflect.generics.reflectiveObjects.WildcardTypeImpl]
         */
    }
}
