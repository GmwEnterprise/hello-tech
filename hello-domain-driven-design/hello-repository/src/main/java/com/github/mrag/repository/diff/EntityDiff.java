package com.github.mrag.repository.diff;

import com.github.mrag.common.Entity;
import com.github.mrag.common.Identifier;
import com.github.mrag.common.utils.CommonUtils;
import com.github.mrag.common.utils.ReflectUtils;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.*;

public class EntityDiff<T extends Entity<ID>, ID extends Identifier> implements Diff<T, ID> {

    @Getter
    private final DiffType diffType;

    private final Class<T> valueType;

    @Getter
    private final T oldValue, newValue;

    private final Map<String, Diff<Entity<Identifier>, Identifier>> diffMap = new HashMap<>();

    EntityDiff(T entity, T snapshot) {
        if (entity == null && snapshot != null) {
            valueType = (Class<T>) snapshot.getClass();
            diffType = DiffType.Removed;
            oldValue = snapshot;
            newValue = null;
        } else if (entity != null && snapshot == null) {
            valueType = (Class<T>) entity.getClass();
            diffType = DiffType.Added;
            oldValue = null;
            newValue = entity;
        } else if (entity != null && Objects.equals(entity.getId(), snapshot.getId())) {
            valueType = (Class<T>) entity.getClass();
            int selfModifiedCount = calculate(entity, snapshot);
            if (selfModifiedCount == 0) {
                diffType = DiffType.NoChanges;
            } else {
                diffType = DiffType.Modified;
            }
            oldValue = snapshot;
            newValue = entity;
        } else {
            throw new NullPointerException("实体与快照均为空");
        }
    }

    public <ST extends Entity<SID>, SID extends Identifier> Diff<ST, SID> getDiff(String name) {
        return (Diff<ST, SID>) diffMap.get(name);
    }

    private int calculate(T entity, T snapshot) {
        // 比对entity与快照的每一个field值是否一致
        int selfModifiedCount = 0;
        for (Field member : ReflectUtils.getAllFields(valueType)) {

            // 获取field的类型 memberType
            Class<?> memberType = member.getType();
            if (Entity.class.isAssignableFrom(memberType)) {
                // 嵌套实体
                diffMap.put(member.getName(), new EntityDiff<>(
                        ((Entity<Identifier>) ReflectUtils.getFieldValue(member, entity)),
                        ((Entity<Identifier>) ReflectUtils.getFieldValue(member, snapshot))));
                continue;
            }
            if (Collection.class.isAssignableFrom(memberType)) {
                // 集合类
                // 判断集合类元素是否为Entity
                Class<?> genericType = ReflectUtils.getGenericTypeClass(member)[0];
                if (Entity.class.isAssignableFrom(genericType)) {
                    // 集合嵌套实体
                    Collection<Entity<Identifier>> entityFieldValue = (Collection<Entity<Identifier>>) ReflectUtils.getFieldValue(member, entity);
                    Collection<Entity<Identifier>> snapshotFieldValue = (Collection<Entity<Identifier>>) ReflectUtils.getFieldValue(member, snapshot);
                    List<Diff<Entity<Identifier>, Identifier>> addedList = calculateAdded(
                            CommonUtils.returnNvl(entityFieldValue, Collections.emptyList()),
                            CommonUtils.returnNvl(snapshotFieldValue, Collections.emptyList()));
                    List<Diff<Entity<Identifier>, Identifier>> lastList = calculateRemovedAndModified(entityFieldValue, snapshotFieldValue);
                    List<Diff<Entity<Identifier>, Identifier>> diffList = new ArrayList<>(addedList.size() + lastList.size());
                    diffList.addAll(addedList);
                    diffList.addAll(lastList);
                    diffMap.put(member.getName(), new ListDiff<>(diffList));
                    continue;
                }
            }
            if (Map.class.isAssignableFrom(memberType)) {
                // 映射类
                Class<?> genericType = ReflectUtils.getGenericTypeClass(member)[1];
                if (Entity.class.isAssignableFrom(genericType)) {
                    // 映射嵌套实体
                    Map<String, Entity<Identifier>> entityFieldValue = (Map<String, Entity<Identifier>>) ReflectUtils.getFieldValue(member, entity);
                    Map<String, Entity<Identifier>> snapshotFieldValue = (Map<String, Entity<Identifier>>) ReflectUtils.getFieldValue(member, snapshot);
                    Map<String, Diff<Entity<Identifier>, Identifier>> addedMap = calculateAdded(
                            CommonUtils.returnNvl(entityFieldValue, Collections.emptyMap()),
                            CommonUtils.returnNvl(snapshotFieldValue, Collections.emptyMap()));
                    Map<String, Diff<Entity<Identifier>, Identifier>> lastMap = calculateRemovedAndModified(
                            CommonUtils.returnNvl(entityFieldValue, Collections.emptyMap()),
                            CommonUtils.returnNvl(snapshotFieldValue, Collections.emptyMap()));
                    addedMap.forEach(diffMap::put);
                    lastMap.forEach(diffMap::put);
                    continue;
                }
            }

            // 普通field，直接equals
            if (Objects.equals(ReflectUtils.getFieldValue(member, entity), ReflectUtils.getFieldValue(member, snapshot))) {
                selfModifiedCount++;
            }
        }
        return selfModifiedCount;
    }

    private Map<String, Diff<Entity<Identifier>, Identifier>> calculateRemovedAndModified(Map<String, Entity<Identifier>> entityMap,
                                                                                          Map<String, Entity<Identifier>> snapshotMap) {
        HashMap<String, Diff<Entity<Identifier>, Identifier>> map = new HashMap<>();
        for (String snapshotKey : snapshotMap.keySet()) {
            Optional<String> existEntityKey = entityMap.keySet().stream().filter(entityKey -> Objects.equals(entityKey, snapshotKey) &&
                    Objects.equals(entityMap.get(entityKey).getId(), snapshotMap.get(snapshotKey).getId())).findFirst();
            if (existEntityKey.isEmpty()) {
                map.put(snapshotKey, new EntityDiff<>(null, snapshotMap.get(snapshotKey)));
            } else {
                map.put(snapshotKey, new EntityDiff<>(entityMap.get(existEntityKey.get()), snapshotMap.get(snapshotKey)));
            }
        }
        return map;
    }

    private List<Diff<Entity<Identifier>, Identifier>> calculateRemovedAndModified(Collection<Entity<Identifier>> entityList,
                                                                                   Collection<Entity<Identifier>> snapshotList) {
        ArrayList<Diff<Entity<Identifier>, Identifier>> diffs = new ArrayList<>();
        for (Entity<Identifier> snapshot : snapshotList) {
            Optional<Entity<Identifier>> existEntity = entityList.stream().filter(entity -> Objects.equals(entity.getId(), snapshot.getId())).findFirst();
            if (existEntity.isEmpty()) {
                diffs.add(new EntityDiff<>(null, snapshot));
            } else {
                diffs.add(new EntityDiff<>(existEntity.get(), snapshot));
            }
        }
        return diffs;
    }

    private Map<String, Diff<Entity<Identifier>, Identifier>> calculateAdded(Map<String, Entity<Identifier>> entityMap,
                                                                             Map<String, Entity<Identifier>> snapshotMap) {
        HashMap<String, Diff<Entity<Identifier>, Identifier>> map = new HashMap<>();
        for (String entityKey : entityMap.keySet()) {
            if (snapshotMap.keySet().stream().noneMatch(snapshotKey -> Objects.equals(entityKey, snapshotKey) &&
                    Objects.equals(entityMap.get(entityKey).getId(), snapshotMap.get(snapshotKey).getId()))) {
                map.put(entityKey, new EntityDiff<>(entityMap.get(entityKey), null));
            }
        }
        return map;
    }

    private List<Diff<Entity<Identifier>, Identifier>> calculateAdded(Collection<Entity<Identifier>> entityList,
                                                                      Collection<Entity<Identifier>> snapshotList) {
        ArrayList<Diff<Entity<Identifier>, Identifier>> diffs = new ArrayList<>();
        for (Entity<Identifier> entity : entityList) {
            if (snapshotList.stream().noneMatch(snapshot -> Objects.equals(snapshot.getId(), entity.getId()))) {
                diffs.add(new EntityDiff<>(entity, null));
            }
        }
        return diffs;
    }
}
