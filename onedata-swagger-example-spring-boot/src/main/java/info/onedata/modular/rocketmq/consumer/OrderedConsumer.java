package info.onedata.modular.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: huangwei
 * @Date: 2021/12/1 16:08
 * @Description: OrderedConsumer
 * @Version 1.0.0
 */
public class OrderedConsumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group_name");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

//        consumer.subscribe("TopicTest", "TagA || TagC || TagD");
        consumer.subscribe("TopicTest", "*");
        consumer.setNamesrvAddr("localhost:9876");

        consumer.registerMessageListener(new MessageListenerOrderly() {

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                MessageExt messageExt = msgs.get(0);
                System.out.printf("%s, %s, %s%n", Thread.currentThread().getName(), "queueId: " + messageExt.getQueueId(),
                        new String(messageExt.getBody(), StandardCharsets.UTF_8));
                try {
                    TimeUnit.MILLISECONDS.sleep(((long) (Math.random() * 100 + 10)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
