package com.github.mrag.repository;

import com.github.mrag.common.Aggregate;
import com.github.mrag.common.Identifier;

public final class DiffUtils {

    public static <T extends Aggregate<ID>, ID extends Identifier> EntityDiff diff(T snapshot, T aggregate) {
        return null;
    }
}
