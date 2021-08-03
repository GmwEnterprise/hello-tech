package com.github.mrag.common;

import java.io.Serializable;

/**
 * ID类型DP的marker接口
 */
public interface Identifier<ID_TYPE> extends Serializable {

    ID_TYPE getId();
}
