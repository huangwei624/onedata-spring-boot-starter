package info.onedata.modular.rocketmq.producer;

import com.alibaba.fastjson.JSONObject;
import info.onedata.modular.rocketmq.message.Body;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Auther: huangwei
 * @Date: 2021/12/1 14:52
 * @Description: SyncProducer
 * @Version 1.0.0
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("please_rename_unique_group_name");
        // Specify name server addresses.
        producer.setNamesrvAddr("localhost:9876");
        //Launch the instance.
        producer.start();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Body<String> body = new Body<String>((long)i, "hello world");
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagA" /* Tag */, body.geneBytes());

            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.println("i: " + i + ", success" + JSONObject.toJSONString(sendResult));
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);    // 694
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}