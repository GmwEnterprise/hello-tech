package com.github.mrag.repository;

import com.github.mrag.common.Aggregate;
import com.github.mrag.common.Identifier;
import com.github.mrag.common.diff.DiffUtils;
import com.github.mrag.common.diff.EntityDiff;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
class DbContext<T extends Aggregate<ID>, ID extends Identifier<?>> {

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
            log.debug("追踪实体: ID = {}", aggregate.getId());
            if (snapshotMap.containsKey(aggregate.getId())) {
                merge(aggregate);
            } else {
                snapshotMap.put(aggregate.getId(), SnapshotUtils.snapshot(aggregate));
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
    EntityDiff<T, ID> detectChanges(@NonNull T aggregate) {
        // 没有ID应为insert
        if (aggregate.getId() == null) {
            return null;
        }

        // 没有snapshot则立刻进行一次追踪
        T snapshot = snapshotMap.get(aggregate.getId());
        if (snapshot == null) {
            log.debug("需要update的实体未被追踪");
            // 从业务逻辑来说，当走到这一步意味着执行的时更新实体
            // 更新实体应该从表中查询出实体再进行更新，也就是说必然会存在snapshot
            // 除非人为对对象解除了追踪
            // 这样会导致最后的diff结果为没有变化
            // 就不会执行update了
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
            snapshotMap.put(aggregate.getId(), SnapshotUtils.snapshot(aggregate));
        }
    }
}
