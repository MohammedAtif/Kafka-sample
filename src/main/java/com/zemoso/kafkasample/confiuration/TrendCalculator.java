package com.zemoso.kafkasample.confiuration;

import com.zemoso.kafkasample.consumer.TrendConsumer;
import com.zemoso.kafkasample.pojos.TrendingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TrendCalculator {

    private final static long interval = 1000 * 60 * 60;

    private Map<Integer, TrendingData> trendMap = new HashMap<>();

    @Autowired
    private TrendConsumer trendConsumer;

    @Scheduled(fixedDelay = interval, initialDelay = interval)
    public void calculate(){
        System.out.println("Task creator called");
        List<TrendingData> trendingDataList = trendConsumer.getRawData();
        for(TrendingData trendingData : trendingDataList){
            TrendingData currentData = trendMap.get(trendingData.getId());
            if(currentData != null){
                currentData.addTrendingData(trendingData);
            }else{
                trendMap.put(trendingData.getId(), trendingData);
            }
        }
    }

}
