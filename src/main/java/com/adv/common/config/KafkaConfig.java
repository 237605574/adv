package com.adv.common.config;

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
    private Properties sparkConfig;
    private Properties exConfig;
    private final String SPARK_CONFIG_FILE = "sparkKafkaConfig.properties";
    private final String EX_CONFIG_FILE = "exKafkaConfig.properties";

    public static KafkaConfig getInstance() {
        return SingletonHolder.instance;
    }

    private KafkaConfig() {
    }

    private static class SingletonHolder {
        private static final KafkaConfig instance = new KafkaConfig();
    }

    public Properties getSparkConfig() {
        if (sparkConfig == null) {
            init();
        }
        return sparkConfig;
    }

    public Properties getExConfig() {
        if (exConfig == null) {
            init();
        }
        return exConfig;
    }

    public void init() {
        sparkConfig = new Properties();
        exConfig = new Properties();
        InputStream sparkInputStream = this.getClass().getClassLoader().getResourceAsStream(SPARK_CONFIG_FILE);
        InputStream exInputStream = this.getClass().getClassLoader().getResourceAsStream(EX_CONFIG_FILE);
        try {
            sparkConfig.load(sparkInputStream);
            exConfig.load(exInputStream);
        } catch (IOException e) {
            LOG.error("读取配置错误");
        }
    }
}
