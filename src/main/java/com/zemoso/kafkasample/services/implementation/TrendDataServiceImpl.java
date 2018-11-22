package com.zemoso.kafkasample.services.implementation;

import com.zemoso.kafkasample.consumer.TrendConsumer;
import com.zemoso.kafkasample.datasource.BurstData;
import com.zemoso.kafkasample.pojos.TrendingData;
import com.zemoso.kafkasample.producer.TrendProducer;
import com.zemoso.kafkasample.repositories.BurstDataRepository;
import com.zemoso.kafkasample.services.interfaces.TrendDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class TrendDataServiceImpl implements TrendDataService {

    @Value("${kafka.topic.raw.trending}")
    private String TRENDING_TOPIC;

    @Autowired BurstDataRepository burstDataRepository;

    @Autowired private TrendProducer trendProducer;
    @Autowired private TrendConsumer trendConsumer;

    @Override
    public String setTrendData(TrendingData trendData) {
        trendProducer.sendRawData(TRENDING_TOPIC, trendData.getId());
        return "success";
    }

    @Override
    public List<TrendingData> getRawTrendingData() {
        return trendConsumer.getRawData();
    }

    @Override
    public List<TrendingData> getProcessedData() {
        return trendConsumer.getProcessedData();
    }

    public Iterable<BurstData> getBurstData() {
        return null;
    }
}
