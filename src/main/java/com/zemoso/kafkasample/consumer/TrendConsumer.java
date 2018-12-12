package com.zemoso.kafkasample.consumer;

import com.zemoso.kafkasample.pojos.TrendingData;
import com.zemoso.kafkasample.utils.Constants;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TrendConsumer {

    @Autowired
    @Qualifier(Constants.RAW_TREND_CONSUMER)
    private KafkaConsumer<String, TrendingData> rawTrendConsumer;

    @Autowired
    private Comparator<TrendingData> trendingDataComparator;

    public TrendConsumer() {
    }

    public List<TrendingData> getRawData(){
        List<TrendingData> trendingData = new ArrayList<>();
        ConsumerRecords<String, TrendingData> records = rawTrendConsumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord<String, TrendingData> record : records) {
            System.out.println("Record received is : "+record.value());
            trendingData.add(record.value());
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
