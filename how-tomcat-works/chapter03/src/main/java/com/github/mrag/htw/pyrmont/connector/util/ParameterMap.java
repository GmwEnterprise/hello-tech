package com.github.mrag.htw.pyrmont.connector.util;

import org.apache.naming.StringManager;

import java.io.Serializable;
import java.util.*;

public final class ParameterMap<K, V> implements Map<K, V>, Serializable {
    private static final long serialVersionUID = 2L;
    private final Map<K, V> delegatedMap;
    private final Map<K, V> unmodifiableDelegatedMap;

    public ParameterMap() {
        delegatedMap = new LinkedHashMap<>();
        unmodifiableDelegatedMap = Collections.unmodifiableMap(delegatedMap);
    }

    public ParameterMap(int initialCapacity) {
        delegatedMap = new LinkedHashMap<>(initialCapacity);
        unmodifiableDelegatedMap = Collections.unmodifiableMap(delegatedMap);
    }

    public ParameterMap(int initialCapacity, float loadFactor) {
        delegatedMap = new LinkedHashMap<>(initialCapacity, loadFactor);
        unmodifiableDelegatedMap = Collections.unmodifiableMap(delegatedMap);
    }

    public ParameterMap(Map<K, V> map) {
        delegatedMap = new LinkedHashMap<>(map);
        unmodifiableDelegatedMap = Collections.unmodifiableMap(delegatedMap);
    }

    private boolean locked = false;

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    private static final StringManager sm = StringManager.getManager("org.apache.catalina.util");

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if this map is currently locked
     */
    @Override
    public void clear() {
        checkLocked();
        delegatedMap.clear();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if this map is currently locked
     */
    @Override
    public V put(K key, V value) {
        checkLocked();
        return delegatedMap.put(key, value);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if this map is currently locked
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        checkLocked();
        delegatedMap.putAll(map);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if this map is currently locked
     */
    @Override
    public V remove(Object key) {
        checkLocked();
        return delegatedMap.remove(key);
    }

    private void checkLocked() {
        if (locked) {
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        }
    }

    @Override
    public int size() {
        return delegatedMap.size();
    }

    @Override
    public boolean isEmpty() {
        return delegatedMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return delegatedMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return delegatedMap.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return delegatedMap.get(key);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns an <strong>unmodifiable</strong> {@link Set} view of the keys
     * contained in this map if it is locked.
     */
    @Override
    public Set<K> keySet() {
        if (locked) {
            return unmodifiableDelegatedMap.keySet();
        }
        return delegatedMap.keySet();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns an <strong>unmodifiable</strong> {@link Collection} view of the
     * values contained in this map if it is locked.
     */
    @Override
    public Collection<V> values() {
        if (locked) {
            return unmodifiableDelegatedMap.values();
        }
        return delegatedMap.values();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns an <strong>unmodifiable</strong> {@link Set} view of the mappings
     * contained in this map if it is locked.
     */
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (locked) {
            return unmodifiableDelegatedMap.entrySet();
        }
        return delegatedMap.entrySet();
    }
}

