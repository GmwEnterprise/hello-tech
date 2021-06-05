package com.github.mrag.rocketmqdemo;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author Gmw
 */
public class OnewayProducer {

    public static void main(String[] args) throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();

        for (int i = 0; i < 5; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message(
                    "TopicTest", "TagA",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            // 发送单向消息，没有任何返回结果
            producer.sendOneway(msg);
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}
