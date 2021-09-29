package com.example;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class TestReferenceQueue {

    public static void main(String[] args) throws InterruptedException {
        // 申请一个阻塞队列
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();

        // 用于检测入队情况的线程
        Runnable task = () -> {
            try {
                int count = 0;
                WeakReference<byte[]> k;
                // remove方法是阻塞的，直到成功返回一个元素为止
                while ((k = (WeakReference) queue.remove()) != null) {
                    System.out.println((count++) + "回收了:" + k);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true); // 保证程序能顺利结束
        thread.start();

        Object value = new Object();
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            byte[] bytes = new byte[1024 * 1024]; // 占用 1M 内存，循环结束总共申请了10G左右内存
            WeakReference<byte[]> weakRef = new WeakReference<>(bytes, queue);
            map.put(weakRef, value);
            System.out.println("put " + i);
        }
        System.out.println(map.size()); // 打印：10000
    }
}
