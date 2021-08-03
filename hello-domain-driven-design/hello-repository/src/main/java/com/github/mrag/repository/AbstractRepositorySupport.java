package com.github.mrag.repository;

import com.github.mrag.common.Aggregate;
import com.github.mrag.common.BaseRepository;
import com.github.mrag.common.Identifier;
import com.github.mrag.repository.diff.EntityDiff;
import lombok.Getter;
import lombok.NonNull;

public abstract class AbstractRepositorySupport<T extends Aggregate<ID>, ID extends Identifier<?>>
        implements BaseRepository<T, ID> {

    @Getter
    private final Class<T> targetClass;

    /**
     * 负责维护snapshot
     */
    private final AggregateManager<T, ID> aggregateManager;

    protected AbstractRepositorySupport(Class<T> targetClass) {
        this.targetClass = targetClass;
        this.aggregateManager = AggregateManager.newInstance(targetClass);
    }

    /**
     * 子类repository实现
     */
    protected abstract void onInsert(T aggregate);

    /**
     * 子类repository实现
     */
    protected abstract T onSelect(ID id);

    /**
     * 子类repository实现
     */
    protected abstract void onUpdate(T aggregate, EntityDiff<T, ID> diff);

    /**
     * 子类repository实现
     */
    protected abstract void onDelete(T aggregate);

    @Override
    public void attach(@NonNull T aggregate) {
        aggregateManager.attach(aggregate);
    }

    @Override
    public void detach(@NonNull T aggregate) {
        aggregateManager.detach(aggregate);
    }

    @Override
    public T find(@NonNull ID id) {
        T aggregate = onSelect(id);
        if (aggregate != null) {
            // 让查询出来的对象被追踪
            attach(aggregate);
        }
        return aggregate;
    }

    @Override
    public void remove(@NonNull T aggregate) {
        this.onDelete(aggregate);
        // 停止追踪
        this.detach(aggregate);
    }

    @Override
    public void save(@NonNull T aggregate) {
        // 没有ID则直接插入
        if (aggregate.getId() == null) {
            onInsert(aggregate);
            attach(aggregate);
            return;
        }

        // diff
        EntityDiff<T, ID> diff = aggregateManager.detectChanges(aggregate);
        if (diff == null) {
            return;
        }

        // 更新
        this.onUpdate(aggregate, diff);
        aggregateManager.merge(aggregate);
    }
}
