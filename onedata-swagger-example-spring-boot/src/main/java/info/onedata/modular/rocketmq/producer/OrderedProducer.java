package info.onedata.modular.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * @Auther: huangwei
 * @Date: 2021/12/1 16:06
 * @Description: OrderedProducer
 * @Version 1.0.0
 */
public class OrderedProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("example_group_name");
        //System.out.println(System.getProperty("com.rocketmq.remoting.client.closeSocketIfTimeout", "true"));
        //Launch the instance.
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for (int i = 0; i < 100; i++) {
            final int orderId = i;
            // Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest", ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    if (orderId == 0) {
                        System.out.println("queue size : " + mqs.size());
                    }
                    // first queue
                    return mqs.get(0);
                }
            }, orderId);
        }
        //server shutdown
        producer.shutdown();
    }
}
