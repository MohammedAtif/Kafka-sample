package com.zemoso.kafkasample.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class TrendProducer {

    @Autowired
    private KafkaTemplate<String, Integer> kafkaTemplate;

    public void send(String topic, Integer payload){
        kafkaTemplate.send(topic, payload);
    }

}
