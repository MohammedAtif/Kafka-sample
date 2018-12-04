package com.zemoso.kafkasample.configuration;

import com.zemoso.kafkasample.consumer.TrendConsumer;
import com.zemoso.kafkasample.pojos.TrendingData;
import com.zemoso.kafkasample.repositories.TrendDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TrendCalculator {

    private final static long interval = 30000;

    private Map<Integer, TrendingData> trendMap = new HashMap<>();

    @Autowired
    private TrendConsumer trendConsumer;

    @Autowired
    private TrendDataRepository trendDataRepository;

    @Autowired
    private Comparator<TrendingData> trendingDataComparator;

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
        if(trendMap.size() > 0){
            List<TrendingData> processedData = new ArrayList<>();
            for(Map.Entry<Integer, TrendingData> mapEntry : trendMap.entrySet()){
                System.out.println("New trend processed : "+mapEntry.getValue());
                processedData.add(mapEntry.getValue());
            }
            processedData.sort(trendingDataComparator);
            trendDataRepository.saveTrendingData(processedData);
        }
        trendMap.clear();
    }

}
