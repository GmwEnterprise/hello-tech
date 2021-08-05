package com.github.mrag.common.utils;

import lombok.NonNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ReflectUtils {

    public static List<Field> getAllFields(Class<?> containerType) {
        List<Field> results = new ArrayList<>();
        Class<?> type = unwrapAnonymous(containerType);
        while (type != null) {
            results.addAll(Arrays.asList(type.getDeclaredFields()));
            type = type.getSuperclass();
        }
        return results;
    }

    public static Class<?> unwrapAnonymous(Class<?> containerType) {
        Field[] declaredFields = containerType.getDeclaredFields();
        if (Arrays.stream(declaredFields).anyMatch(field -> "this$0".equals(field.getName()))) {
            // 匿名内部类
            return containerType.getSuperclass();
        }
        return containerType;
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

    /**
     * 获取输入成员的泛型类型。比如一个{@code class InfoOrder {}}中存在一个容器成员{@code List<String> orderItems},
     * 那么输入orderItems的Field对象将会返回 {@code List<String>}所代表的Type实例，这里是ParameterizedType
     *
     * @param field 输入成员
     * @return 结果数组
     */
    public static Class<?>[] getGenericTypeClass(Field field) {
        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
            return Arrays.stream(actualTypeArguments)
                         .map(type -> {
                             if (type instanceof Class) {
                                 return ((Class<?>) type);
                             }
                             if (type instanceof ParameterizedType) {
                                 return ((Class<?>) ((ParameterizedType) type).getRawType());
                             }
                             return null;
                         }).toArray(Class[]::new);
        }
        return new Class[0];
    }

    /**
     * 判断输入类型是否为基本类型或其包装类型
     *
     * @param type 输入类型，不能为空
     * @return 布尔结果
     */
    public static boolean isWrapClassOrPrimitiveClass(@NonNull Class<?> type) {
        if (type.isPrimitive()) {
            return true;
        }
        try {
            Field primitiveType = type.getField("TYPE");
            return ((Class<?>) primitiveType.get(null)).isPrimitive();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }

    public static <R> R newInstance(@NonNull Class<R> rtype, @NonNull Object[] params) {
        Class<?>[] paramTypes = Arrays.stream(params).map(Object::getClass).toArray(Class<?>[]::new);
        Constructor<R> constructor;
        try {
            constructor = rtype.getConstructor(paramTypes);
            constructor.setAccessible(true);
            return constructor.newInstance(params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
