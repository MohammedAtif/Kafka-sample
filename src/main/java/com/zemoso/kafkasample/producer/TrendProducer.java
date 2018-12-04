package com.zemoso.kafkasample.producer;

import com.zemoso.kafkasample.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;

public class TrendProducer {

    @Autowired
    @Qualifier(Constants.RAW_TREND_PRODUCER)
    private KafkaTemplate<String, Integer> rawTrendTemplate;

    public void sendRawData(String topic, Integer payload){
        rawTrendTemplate.send(topic, payload);
    }
}
