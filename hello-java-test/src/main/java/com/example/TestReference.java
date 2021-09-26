package com.example;

import java.lang.ref.WeakReference;

public class TestReference {

    public static void main(String[] args) {
        WeakReference<String> weakStr = new WeakReference<>("HELLO WORLD");
        WeakReference<String> weakRef = new WeakReference<>(String.format("%s", "hello world"));

        System.gc();
        System.out.println(weakStr.get()); // HELLO WORLD
        System.out.println(weakRef.get()); // null
    }
}
