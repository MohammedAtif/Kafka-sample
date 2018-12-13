package com.zemoso.kafkasample.configuration;

import com.zemoso.kafkasample.pojos.TrendingData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;

@Configuration
public class ResourceProvider {

    @Bean
    public Comparator<TrendingData> trendingDataComparator(){
        return Comparator.comparingInt(TrendingData::getScore);
    }

}
