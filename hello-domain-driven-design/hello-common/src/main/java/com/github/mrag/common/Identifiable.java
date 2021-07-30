package com.github.mrag.common;

/**
 * 要求实现类必须返回一个ID类型的DP
 */
public interface Identifiable<ID extends Identifier> {

    ID getId();
}
