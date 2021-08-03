package com.github.mrag.common;

import java.io.Serializable;

/**
 * 要求实现类必须返回一个ID类型的DP
 */
public interface Identifiable<ID extends Identifier<?>> extends Serializable {

    ID getId();
}
