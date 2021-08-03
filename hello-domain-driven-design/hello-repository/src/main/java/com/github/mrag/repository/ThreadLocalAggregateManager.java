package com.github.mrag.repository;

import com.github.mrag.common.Aggregate;
import com.github.mrag.common.Identifier;
import com.github.mrag.common.diff.EntityDiff;

class ThreadLocalAggregateManager<T extends Aggregate<ID>, ID extends Identifier<?>>
        extends AggregateManager<T, ID> {

    private final ThreadLocal<DbContext<T, ID>> context;
    // private final Class<T> targetClass;

    public ThreadLocalAggregateManager(Class<T> targetClass) {
        // this.targetClass = targetClass;
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
