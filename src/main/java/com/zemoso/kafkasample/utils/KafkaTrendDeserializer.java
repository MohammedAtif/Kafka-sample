package com.zemoso.kafkasample.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoso.kafkasample.pojos.TrendingData;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class KafkaTrendDeserializer implements Deserializer<TrendingData> {

    private final ObjectMapper objectMapper;

    public KafkaTrendDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public TrendingData deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, TrendingData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {

    }
}
