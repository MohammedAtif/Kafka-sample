package com.zemoso.kafkasample.confiuration;

import com.zemoso.kafkasample.services.implementation.TrendDataServiceImpl;
import com.zemoso.kafkasample.services.interfaces.TrendDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public TrendDataService getTrendDatService() {
        return new TrendDataServiceImpl();
    }

}
