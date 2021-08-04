package com.github.mrag.repository;

import com.github.mrag.common.Aggregate;
import com.github.mrag.common.Identifier;
import com.github.mrag.common.diff.EntityDiff;

/**
 * 使用ThreadLocal包装DbContext做到线程安全
 *
 * @param <T>  聚合实体类型
 * @param <ID> 主键类型
 */
class ThreadLocalAggregateManager<T extends Aggregate<ID>, ID extends Identifier<?>>
        extends AggregateManager<T, ID> {

    private final ThreadLocal<DbContext<T, ID>> context;

    public ThreadLocalAggregateManager(Class<T> targetClass) {
        this.context = ThreadLocal.withInitial(() -> new DbContext<>(targetClass));
    }

    @Override
    public void attach(T aggregate) {
        context.get().attach(aggregate);
    }

    @Override
    public void detach(T aggregate) {
        context.get().detach(aggregate);
    }

    @Override
    public EntityDiff<T, ID> detectChanges(T aggregate) {
        return context.get().detectChanges(aggregate);
    }

    @Override
    public void merge(T aggregate) {
        context.get().merge(aggregate);
    }
}
