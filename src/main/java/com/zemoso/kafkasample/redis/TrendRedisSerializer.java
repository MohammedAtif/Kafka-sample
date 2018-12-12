package com.zemoso.kafkasample.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoso.kafkasample.pojos.TrendingData;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;

public class TrendRedisSerializer implements RedisSerializer<TrendingData> {

    private final ObjectMapper objectMapper;

    public TrendRedisSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public byte[] serialize(TrendingData rawData) throws SerializationException {
        if(rawData != null) {
            try {
                return objectMapper.writeValueAsString(rawData).getBytes();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public TrendingData deserialize(byte[] bytes) throws SerializationException {
        if(bytes != null) {
            String data = new String(bytes);
            try {
                return objectMapper.readValue(data, TrendingData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
