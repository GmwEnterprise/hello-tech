package com.github.mrag.htw.pyrmont.connector.util;

import java.util.Enumeration;
import java.util.Iterator;

public class Enumerator<E> implements Enumeration<E> {
    private final Iterator<E> iterator;

    public Enumerator(Iterable<E> container) {
        iterator = container.iterator();
    }

    @Override
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    @Override
    public E nextElement() {
        return iterator.next();
    }
}
