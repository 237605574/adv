package com.adv.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author lurongzhi
 */
public class KafkaConfig {
    private static final Logger LOG = LogManager.getLogger(KafkaConfig.class);
    private Properties properties;
    private final String FILE_NAME = "kafkaConfig.properties";

    public static KafkaConfig getInstance() {
        return SingletonHolder.instance;
    }

    private KafkaConfig() {
    }

    private static class SingletonHolder {
        private static final KafkaConfig instance = new KafkaConfig();
    }

    public Properties getProperties() {
        if (properties == null) {
            init();
        }
        return properties;
    }

    public void init() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            LOG.error("读取kafka配置错误");
        }
    }
}
