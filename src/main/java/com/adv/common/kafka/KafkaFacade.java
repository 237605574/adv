package com.adv.common.kafka;


import com.adv.common.config.KafkaConfig;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

/**
 * @author lurongzhi
 */
public class KafkaFacade {
    private Producer<String, String> sparkProducer;
    private ExKafkaThread exKafkaThread;


    public static KafkaFacade getInstance() {
        return SingletonHolder.instance;
    }

    private KafkaFacade() {
        init();
    }

    private static class SingletonHolder {
        private static final KafkaFacade instance = new KafkaFacade();
    }

    private void init() {
        exKafkaThread = new ExKafkaThread();
        Properties sparkConfig = KafkaConfig.getInstance().getSparkConfig();
//        sparkProducer = new KafkaProducer<>(sparkConfig);
    }

    public void start() {
        new Thread(exKafkaThread).start();
    }

}
