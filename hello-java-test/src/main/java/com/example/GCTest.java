package com.example;

import java.lang.ref.WeakReference;

/**
 * vm参数: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
public class GCTest {

    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) {
        testAllocation();
        byte[] newAlloc = new byte[10 * _1M];
    }

    private static void testAllocation() {
        WeakReference<byte[]> alloc1, alloc2, alloc3, alloc4;
        alloc1 = new WeakReference<>(new byte[2 * _1M]);
        alloc2 = new WeakReference<>(new byte[2 * _1M]);
        alloc3 = new WeakReference<>(new byte[2 * _1M]);
        alloc4 = new WeakReference<>(new byte[4 * _1M]); // 超过了新生代 Eden 区大小，产生一次 Minor GC
    }
}
