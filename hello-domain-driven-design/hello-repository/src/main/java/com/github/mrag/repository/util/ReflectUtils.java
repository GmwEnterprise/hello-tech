package com.github.mrag.repository.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ReflectUtils {

    public static List<Field> getAllFields(Class<?> containerType) {
        List<Field> results = new ArrayList<>();
        Class<?> type = containerType;
        while (type != null) {
            results.addAll(Arrays.asList(type.getDeclaredFields()));
            type = type.getSuperclass();
        }
        return results;
    }

    public static Object getFieldValue(Field member, Object container) {
        try {
            return member.get(container);
        } catch (IllegalAccessException e) {
            member.setAccessible(true);
            try {
                return member.get(container);
            } catch (IllegalAccessException ex) {
                // 不可能
                return null;
            }
        }
    }

    public static Class<?>[] getGenericTypeClass(Field field) {
        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {

        }
    }
}
