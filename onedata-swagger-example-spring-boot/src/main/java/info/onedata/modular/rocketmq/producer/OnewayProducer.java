package info.onedata.modular.rocketmq.producer;

import info.onedata.modular.rocketmq.message.Body;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Auther: huangwei
 * @Date: 2021/12/1 14:54
 * @Description: OnewayProducer
 * @Version 1.0.0
 */
public class OnewayProducer {
    public static void main(String[] args) throws Exception{
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        // Specify name server addresses.
        producer.setNamesrvAddr("localhost:9876");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Body<String> body = new Body<String>("hello world");
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagA" /* Tag */, body.geneBytes());
            //Call send message to deliver message to one of brokers.
            producer.sendOneway(msg);
        }
        //Wait for sending to complete
        Thread.sleep(5000);
        producer.shutdown();
    }
}
