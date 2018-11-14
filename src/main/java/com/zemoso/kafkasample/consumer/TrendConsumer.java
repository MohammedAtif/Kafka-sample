package com.zemoso.kafkasample.consumer;

import com.zemoso.kafkasample.pojos.TrendingData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TrendConsumer {

    @Autowired
    private KafkaConsumer<String, Integer> kafkaConsumer;

    public List<TrendingData> getRawData(){
        List<TrendingData> trendingData = new ArrayList<>();
        ConsumerRecords<String, Integer> records = kafkaConsumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord<String, Integer> record : records) {
            System.out.println("Record received is : "+record.value());
            trendingData.add(new TrendingData(record.value()));
        }
        return trendingData;
    }

//    private CountDownLatch latch = new CountDownLatch(1);
//
//    public CountDownLatch getLatch() {
//        return latch;
//    }
//
//    @KafkaListener(topics = "${kafka.topic.trending}")
//    public void receive(Integer payload) {
//        System.out.println("Payload received : "+payload);
//        latch.countDown();
//    }
}
