package com.zemoso.kafkasample.redis;

import com.zemoso.kafkasample.pojos.TrendingData;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;

public class TrendRedisSerializer implements RedisSerializer<TrendingData> {

    @Override
    public byte[] serialize(TrendingData rawData) throws SerializationException {
        if(rawData != null) {
            return rawData.toString().getBytes();
        }else{
            return null;
        }
    }

    @Override
    public TrendingData deserialize(byte[] bytes) throws SerializationException {
        if(bytes != null) {
            String data = new String(bytes);
            try {
                return new TrendingData(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
