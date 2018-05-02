package com.adv.common.kafka;

import com.adv.entity.ResultObj;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @author lurongzhi
 */
public class ObjectDecoder implements Deserializer<ResultObj> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public ResultObj deserialize(String topic, byte[] data) {
        return ResultObj.toResultObj(data);
    }

    @Override
    public void close() {

    }
}
