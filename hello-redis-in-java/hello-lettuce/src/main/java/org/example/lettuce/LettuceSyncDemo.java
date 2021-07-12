package org.example.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class LettuceSyncDemo {

    public static void main(String[] args) throws InterruptedException {
        RedisClient client = RedisClient.create("redis://123456@localhost:6379/1");
        StatefulRedisConnection<String, String> connect = client.connect();

        // 同步
        RedisCommands<String, String> commandSync = connect.sync();
        for (int i = 1; i <= 10; i++) {
            System.out.println(commandSync.incrby("incr_lettuce", i * 10));
            Thread.sleep(500);
        }

        connect.close();
        client.shutdown();
    }
}
