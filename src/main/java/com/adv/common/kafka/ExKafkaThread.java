package com.adv.common.kafka;

import com.adv.common.config.KafkaConfig;
import com.adv.entity.ResultObj;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

/**
 * @author lurongzhi
 */
public class ExKafkaThread implements Runnable {
    private Consumer<Long, ResultObj> exConsumer;

    public void init() {
        Properties exConfig = KafkaConfig.getInstance().getExConfig();
        exConsumer = new KafkaConsumer<>(exConfig);
        exConsumer.subscribe(Arrays.asList(exConfig.getProperty("topic")), new ConsumerRebalanceListener() {
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
            }

            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                //将偏移设置到最开始
                exConsumer.seekToBeginning(collection);
            }
        });
    }


    public void run() {
        if (exConsumer == null) {
            System.out.println("runing");
            init();
        }
        while (true) {
            ConsumerRecords<Long, ResultObj> records = exConsumer.poll(100);
            for (ConsumerRecord<Long, ResultObj> record : records) {
                System.out.println("print record start");
                System.out.printf("offset = %d, key = %s, value = %d,msg=%s%n", record.offset(), record.key(), record.value().getCode(), record.value().getMsg());
                System.out.println("data=" + record.value().getData());
                System.out.println("print record end");

            }

        }
    }
}
