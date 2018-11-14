package com.zemoso.kafkasample.confiuration;

import com.zemoso.kafkasample.consumer.TrendConsumer;
import com.zemoso.kafkasample.pojos.TrendingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrendCalculator {

    private final static long interval = 1000 * 60 * 60;

    @Autowired
    private TrendConsumer trendConsumer;

    @Scheduled(fixedDelay = interval, initialDelay = interval)
    public void calculate(){
        System.out.println("Task creator called");
        List<TrendingData> trendingDataList = trendConsumer.getRawData();
    }

}
