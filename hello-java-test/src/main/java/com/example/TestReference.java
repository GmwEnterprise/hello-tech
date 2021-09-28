package com.example;

import java.lang.ref.WeakReference;

public class TestReference {

    private final String value;

    public TestReference(String value) {this.value = value;}

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("%s - %s%n", Thread.currentThread(), "finalize!");
    }

    public static void main(String[] args) {
        WeakReference<String> weakStr = new WeakReference<>("HELLO WORLD");
        WeakReference<String> weakRef = new WeakReference<>(String.format("%s", "hello world"));
        WeakReference<TestReference> weak = new WeakReference<>(new TestReference("FINALIZE"));

        System.gc();
        System.out.printf("%s - %s%n", Thread.currentThread(), weakStr.get());
        System.out.printf("%s - %s%n", Thread.currentThread(), weakRef.get());
        System.out.printf("%s - %s%n", Thread.currentThread(), weak.get());
    }
}
