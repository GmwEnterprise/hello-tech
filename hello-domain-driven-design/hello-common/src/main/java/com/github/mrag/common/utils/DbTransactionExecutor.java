package com.github.mrag.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Slf4j
@Component
public class DbTransactionExecutor {

    @Transactional
    public void execute(Runnable task) {
        log.debug("执行事务: {}", Thread.currentThread());
        task.run();
    }

    @Transactional
    public <R> R execute(Supplier<R> task) {
        return task.get();
    }
}
