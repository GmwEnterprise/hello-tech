package org.example.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.concurrent.CountDownLatch;

public class LettuceAsyncDemo {

    public static void main(String[] args) throws InterruptedException {
        // Syntax: redis://[password@]host[:port][/databaseNumber]
        RedisClient client = RedisClient.create("redis://123456@localhost:6379/0");
        StatefulRedisConnection<String, String> connect = client.connect();

        // 异步
        RedisAsyncCommands<String, String> async = connect.async();
        int count = 100;
        CountDownLatch countDown = new CountDownLatch(count);
        for (int i = 1; i <= count; i++) {
            int finalI = i;
            async.incrby("incr_lettuce", i * 10L).whenComplete((value, error) -> {
                if (error != null)
                    System.out.printf("[%d] error = %s%n", finalI, error.getMessage());
                else
                    System.out.printf("[%d] = %d\n", finalI, value);
                countDown.countDown();
            });
        }

        countDown.await();
        connect.close();
        client.shutdown();
    }
}
