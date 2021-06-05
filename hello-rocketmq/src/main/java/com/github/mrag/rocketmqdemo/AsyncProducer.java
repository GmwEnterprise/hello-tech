package com.github.mrag.rocketmqdemo;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 异步发送消息
 *
 * @author Gmw
 */
public class AsyncProducer {

    static {
        System.setProperty("rocketmq.client.logUseSlf4j", "true");
    }

    public static void main(String[] args) throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();
        // 设置异步发送失败的重试次数
        producer.setRetryTimesWhenSendAsyncFailed(0);

        int messageCount = 100;
        // 根据消息数量实例化倒计时计算器
        CountDownLatch2 countDownLatch = new CountDownLatch2(messageCount);
        for (int i = 0; i < messageCount; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message(
                    "TopicTest", "TagA", "OrderID188",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET)
            );

            // 异步发送
            int index = i;
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                    countDownLatch.countDown();
                }

                @Override
                public void onException(Throwable e) {
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                    countDownLatch.countDown();
                }
            });
        }

        // 等所有消息发送后再关闭producer
        countDownLatch.await();

        // 如果不再发送消息，关闭Producer实例
        producer.shutdown();
    }
}
