package com.zemoso.kafkasample.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoso.kafkasample.pojos.TrendingData;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;

public class TrendRedisSerializer implements RedisSerializer<TrendingData> {

    private final ObjectMapper objectMapper;

    public TrendRedisSerializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.addMixIn(TrendingData.class, TrendingDataMixin.class);
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
            try {
                return objectMapper.readValue(bytes, TrendingData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
