package com.zemoso.kafkasample.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoso.kafkasample.pojos.TrendingData;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class KafkaTrendSerializer implements Serializer<TrendingData> {

    private final ObjectMapper objectMapper;

    public KafkaTrendSerializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, TrendingData data) {
        try {
            return objectMapper.writeValueAsString(data).getBytes();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {

    }
}
