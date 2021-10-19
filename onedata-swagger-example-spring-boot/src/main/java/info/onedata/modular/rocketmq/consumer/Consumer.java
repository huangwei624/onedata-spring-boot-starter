package info.onedata.modular.rocketmq.consumer;

import com.alibaba.fastjson.JSONObject;
import info.onedata.modular.rocketmq.message.Body;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Auther: huangwei
 * @Date: 2021/12/1 14:55
 * @Description: Consumer
 * @Version 1.0.0
 */
public class Consumer {
    public static void main(String[] args) throws InterruptedException, MQClientException {

        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");

        // Specify name server addresses.
        consumer.setNamesrvAddr("localhost:9876");

        // Subscribe one more more topics to consume.
        consumer.subscribe("TopicTest", "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                /*System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);*/
                /*List<Body> bodyList = msgs.stream()
                        .map(item -> JSONObject.parseObject(new String(item.getBody(), StandardCharsets.UTF_8), Body.class))
                        .collect(Collectors.toList());
                System.out.println(bodyList);*/
                try {
                    TimeUnit.MILLISECONDS.sleep(((long) (Math.random() * 100 + 10)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new String(msgs.get(0).getBody(), StandardCharsets.UTF_8));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
