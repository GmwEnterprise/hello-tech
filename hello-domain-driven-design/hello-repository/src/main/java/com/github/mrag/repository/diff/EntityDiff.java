package com.github.mrag.repository.diff;

import com.github.mrag.common.Entity;
import com.github.mrag.common.Identifier;
import com.github.mrag.repository.util.ReflectUtils;
import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EntityDiff<T extends Entity<ID>, ID extends Identifier> implements Diff<T, ID> {

    @Getter
    private final DiffType diffType;
    private final Class<T> valueType;
    private final T oldValue, newValue;

    private final Map<String, Diff> diffMap = new HashMap<>();

    EntityDiff(T entity, T snapshot) {
        if (entity == null && snapshot != null) {
            diffType = DiffType.Removed;
            oldValue = snapshot;
            newValue = null;
            valueType = (Class<T>) snapshot.getClass();
        } else if (entity != null && snapshot == null) {
            diffType = DiffType.Added;
            oldValue = null;
            newValue = entity;
            valueType = (Class<T>) entity.getClass();
        } else if (entity != null && Objects.equals(entity.getId(), snapshot.getId())) {
            valueType = (Class<T>) entity.getClass();
            calculate(entity, snapshot);
        } else {
            throw new NullPointerException("实体与快照均为空");
        }
    }

    private void calculate(T entity, T snapshot) {
        for (Field member : ReflectUtils.getAllFields(valueType)) {
            Class<?> memberType = member.getType();
            if (Entity.class.isAssignableFrom(memberType)) {
                diffMap.put(member.getName(), new EntityDiff(
                        ((Entity) ReflectUtils.getFieldValue(member, entity)),
                        ((Entity) ReflectUtils.getFieldValue(member, snapshot))));
            } else if (List.class.isAssignableFrom(memberType)) {
                Class<?> genericClassOfListMember = ReflectUtils.getGenericTypeClass(member)[0];
            }
        }
    }
}
