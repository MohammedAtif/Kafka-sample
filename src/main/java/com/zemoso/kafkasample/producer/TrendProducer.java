package com.zemoso.kafkasample.producer;

import com.zemoso.kafkasample.pojos.TrendingData;
import com.zemoso.kafkasample.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;

public class TrendProducer {

    @Autowired
    @Qualifier(Constants.RAW_TREND_PRODUCER)
    private KafkaTemplate<String, TrendingData> rawTrendTemplate;

    public void sendRawData(String topic, TrendingData payload){
        rawTrendTemplate.send(topic, payload);
    }
}
