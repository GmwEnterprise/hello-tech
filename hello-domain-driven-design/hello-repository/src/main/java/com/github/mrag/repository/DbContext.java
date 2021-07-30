package com.github.mrag.repository;

import com.github.mrag.common.Aggregate;
import com.github.mrag.common.Identifier;
import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

class DbContext<T extends Aggregate<ID>, ID extends Identifier> {

    @Getter
    private final Class<? extends T> aggregateClass;

    private final Map<ID, T> snapshotMap = new HashMap<>();

    DbContext(Class<? extends T> aggregateClass) {
        this.aggregateClass = aggregateClass;
    }

    /**
     * 跟踪一个聚合根，直接保存到snapshotMap中；
     * 如果已经存在该快照，那么与之合并
     */
    void attach(@NonNull T aggregate) {
        if (aggregate.getId() != null) {
            if (snapshotMap.containsKey(aggregate.getId())) {
                merge(aggregate);
            }
        }
    }

    /**
     * 解除该聚合根的追踪
     */
    void detach(@NonNull T aggregate) {
        if (aggregate.getId() != null) {
            snapshotMap.remove(aggregate.getId());
        }
    }

    /**
     * 检查聚合根的变化，汇总为EntityDiff实例
     */
    EntityDiff detectChanges(@NonNull T aggregate) {
        // 没有ID应为insert
        if (aggregate.getId() == null) {
            return EntityDiff.EMPTY;
        }

        // 没有snapshot则立刻进行一次追踪
        T snapshot = snapshotMap.get(aggregate.getId());
        if (snapshot == null) {
            attach(aggregate);
        }

        // 比较变化
        return DiffUtils.diff(snapshot, aggregate);
    }

    /**
     * 查询snapshot
     */
    public T find(@NonNull ID id) {
        return snapshotMap.get(id);
    }

    /**
     * 合并变化到snapshot
     */
    void merge(@NonNull T aggregate) {
        if (aggregate.getId() != null) {
            T snapshot = SnapshotUtils.snapshot(aggregate);
            snapshotMap.put(aggregate.getId(), snapshot);
        }
    }
}
