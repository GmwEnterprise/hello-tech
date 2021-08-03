package com.github.mrag.common.diff;

/**
 * 只针对EntityDiff类型，ListDiff不存在DiffType的判断
 */
public enum DiffType {

    Modified,

    Added,

    Removed,

    NoChanges
}
