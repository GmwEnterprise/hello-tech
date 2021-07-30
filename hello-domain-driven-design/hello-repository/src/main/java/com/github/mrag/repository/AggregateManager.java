package com.github.mrag.repository;

import com.github.mrag.common.Aggregate;
import com.github.mrag.common.Identifier;

public abstract class AggregateManager<T extends Aggregate<ID>, ID extends Identifier> {

    public static <ID extends Identifier, T extends Aggregate<ID>>
    AggregateManager<T, ID> newInstance(Class<T> targetClass) {
        return new ThreadLocalAggregateManager<>(targetClass);
    }

    public abstract void attach(T aggregate);

    public abstract void detach(T aggregate);

    public abstract EntityDiff detectChanges(T aggregate);

    public abstract void merge(T aggregate);
}
