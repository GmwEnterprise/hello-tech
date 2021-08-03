package com.github.mrag.common.diff;

import com.github.mrag.common.Entity;
import com.github.mrag.common.Identifier;
import lombok.Getter;

import java.util.Iterator;
import java.util.List;

public class ListDiff<T extends Entity<ID>, ID extends Identifier<?>> implements Diff<T, ID>, Iterable<Diff<T, ID>> {

    @Getter
    private final List<Diff<T, ID>> list;

    public ListDiff(List<Diff<T, ID>> list) {
        this.list = list;
    }

    @Override
    public Iterator<Diff<T, ID>> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return String.valueOf(list);
    }
}
