package com.github.mrag.repository;

import com.github.mrag.common.Aggregate;
import com.github.mrag.common.Identifier;
import org.apache.commons.lang3.SerializationUtils;

public final class SnapshotUtils {

    /**
     * 返回对象的快照copy
     */
    public static <T extends Aggregate<ID>, ID extends Identifier<?>> T snapshot(T aggregate) {
        return SerializationUtils.clone(aggregate);
    }
}
