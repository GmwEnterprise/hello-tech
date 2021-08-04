package com.github.mrag.common.diff;

import com.github.mrag.common.Aggregate;
import com.github.mrag.common.Identifier;

public final class DiffUtils {

    public static <T extends Aggregate<ID>, ID extends Identifier<?>> EntityDiff<T, ID> diff(T snapshot, T aggregate) {
        return new EntityDiff<>(aggregate, snapshot);
    }

    public static <T extends Aggregate<ID>, ID extends Identifier<?>> boolean isEmpty(EntityDiff<T, ID> diff) {
        return diff == null || diff.getDiffType() == DiffType.NoChanges;
    }
}
