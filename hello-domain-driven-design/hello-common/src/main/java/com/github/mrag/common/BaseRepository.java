package com.github.mrag.common;

public interface BaseRepository<T extends Aggregate<ID>, ID extends Identifier<?>> {

    /**
     * 追踪一个aggregate
     */
    void attach(T aggregate);

    /**
     * 停止对aggregate的追踪
     */
    void detach(T aggregate);

    /**
     * 查询aggregate，并设置对其追踪
     */
    T findAndAttach(ID id);

    /**
     * 查询aggregate，并设置对其追踪
     */
    T find(ID id);

    /**
     * 移除aggregate，停止对其追踪
     */
    void remove(T aggregate);

    /**
     * 保存aggregate，重新设置对其追踪
     */
    void save(T aggregate);

    /**
     * 保存aggregate，重新设置对其追踪
     */
    void saveAndDetach(T aggregate);
}
