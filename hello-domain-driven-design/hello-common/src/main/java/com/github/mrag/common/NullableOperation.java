package com.github.mrag.common;

import com.github.mrag.common.utils.CommonUtils;
import com.github.mrag.common.utils.ReflectUtils;

public interface NullableOperation {

    default <R> R nullableSet(Class<R> rtype, Object... constructorParams) {
        if (CommonUtils.everyItemInArrayIsNull(constructorParams)) {
            return null;
        }
        return ReflectUtils.newInstance(rtype, constructorParams);
    }
}
