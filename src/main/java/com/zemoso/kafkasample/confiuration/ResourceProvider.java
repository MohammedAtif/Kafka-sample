package com.zemoso.kafkasample.confiuration;

import com.zemoso.kafkasample.pojos.TrendingData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;

@Configuration
public class ResourceProvider {

    @Bean
    public Comparator<TrendingData> trendingDataComparator(){
        return (o1, o2) -> o2.getScore() - o1.getScore();
    }

}
