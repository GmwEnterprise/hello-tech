package com.github.mrag.helloim.common;

import com.github.mrag.helloim.common.enums.EnumInterface;
import com.github.mrag.helloim.domain.ImUser;

import java.util.ArrayList;
import java.util.Arrays;

public final class Asserts {

    public static void registryMessageNotEmpty(ImUser user) {
        ArrayList<String> params = new ArrayList<>(2);
        if (user.getUsername() == null) {
            params.add("username");
        }
        if (user.getPassword() == null) {
            params.add("password");
        }
        if (params.size() > 0) {
            throw Exceptions.parameterIsEmpty(String.join(", ", params));
        }
    }

    public static <T extends Enum<T>> void implementsEnumInterface(Class<T> type) {
        if (!Arrays.asList(type.getInterfaces()).contains(EnumInterface.class)) {
            String message = "项目中所有枚举必须实现 com.github.mrag.helloim.common.enums.Enums.EnumInterface 接口";
            throw Exceptions.fromSystem(message);
        }
    }
}
