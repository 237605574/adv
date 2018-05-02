package com.adv.common.kafka;

import com.adv.entity.ResultObj;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;


/**
 * @author lurongzhi
 */

public class ObjectEncoder implements Serializer<ResultObj> {


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, ResultObj data) {
        return data.toBytes();
    }

    @Override
    public void close() {

    }
}

