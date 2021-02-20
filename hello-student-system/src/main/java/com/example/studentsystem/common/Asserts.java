package com.example.studentsystem.common;

import java.util.Arrays;

public class Asserts {
    public static <T extends Enum<T>> void implementsEnumInterface(Class<T> type) {
        if (!Arrays.asList(type.getInterfaces()).contains(Enums.EnumInterface.class)) {
            throw new RuntimeException("项目中所有枚举必须实现 EnumInterface 接口");
        }
    }
}
