package com.adv.kafka;

import com.adv.common.config.KafkaConfig;
import com.adv.common.constants.ResultCodes;
import com.adv.common.kafka.ObjectEncoder;
import com.adv.entity.ResultObj;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author lurongzhi
 */
public class KafkaProducerTest {
    public static void main(String[] args) {
        Properties properties = KafkaConfig.getInstance().getExConfig();
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.0.150:9092");
        props.put("acks", "all");
        props.put("value.serializer", ObjectEncoder.class.getName());
        props.put("key.serializer", org.apache.kafka.common.serialization.LongSerializer.class.getName());

        Producer<Long, ResultObj> producer = new KafkaProducer<>(props);
        List<Long> longList = new ArrayList<>();
        longList.add(1L);
        longList.add(2L);
        for (int i = 0; i < 1; i++) {
            producer.send(new ProducerRecord<>(properties.getProperty("topic"), new ResultObj<List>(ResultCodes.SUCCESS, longList)));
        }
    }
}
