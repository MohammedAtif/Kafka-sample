package com.zemoso.kafkasample.repositories.impl;

import com.zemoso.kafkasample.pojos.TrendingData;
import com.zemoso.kafkasample.repositories.TrendDataRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public class TrendDataRedisImpl implements TrendDataRepository {

    @Value("${kafka.topic.processed.trending}")
    private String PROCESSED_TREND;

    private final ListOperations<String, TrendingData> redisListOperation;

    public TrendDataRedisImpl(RedisTemplate<String, TrendingData> redisTemplate) {
        this.redisListOperation = redisTemplate.opsForList();
    }

    @Override
    public void saveTrendingData(List<TrendingData> trendingDataList) {
        while (redisListOperation.leftPop(PROCESSED_TREND) != null);
        redisListOperation.leftPushAll(PROCESSED_TREND, trendingDataList);
    }

    @Override
    public List<TrendingData> getTrendingDataList() {
        Long size = redisListOperation.size(PROCESSED_TREND);
        return redisListOperation.range(PROCESSED_TREND, 0, size);
    }

}
