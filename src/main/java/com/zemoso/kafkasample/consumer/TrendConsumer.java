package com.zemoso.kafkasample.consumer;

import com.zemoso.kafkasample.pojos.TrendingData;
import com.zemoso.kafkasample.utils.Constants;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TrendConsumer {

    @Autowired
    @Qualifier(Constants.RAW_TREND_CONSUMER)
    private KafkaConsumer<String, Integer> rawTrendConsumer;

    @Autowired
    @Qualifier(Constants.PROCESSED_TREND_CONSUMER)
    private KafkaConsumer<String, TrendingData> processedTrendConsumer;

    @Autowired
    private Comparator<TrendingData> trendingDataComparator;

    private String processedDataTopic;

    public TrendConsumer(String processedDataTopic) {
        this.processedDataTopic = processedDataTopic;
    }

    public List<TrendingData> getRawData(){
        List<TrendingData> trendingData = new ArrayList<>();
        ConsumerRecords<String, Integer> records = rawTrendConsumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord<String, Integer> record : records) {
            System.out.println("Record received is : "+record.value());
            trendingData.add(new TrendingData(record.value()));
        }
        return trendingData;
    }

    public List<TrendingData> getProcessedData(){
        ConsumerRecords<String, TrendingData> record = processedTrendConsumer.poll(Duration.ofMillis(100));
        TopicPartition topicPartition = new TopicPartition(processedDataTopic, 0);
        List<TrendingData> trendingData = new ArrayList<>();
        for(ConsumerRecord<String, TrendingData> consumerRecord : record){
            trendingData.add(consumerRecord.value());
        }
        if(trendingData.size() > 0){
            trendingData.sort(trendingDataComparator);
            long currentPosition = processedTrendConsumer.position(topicPartition);
            processedTrendConsumer.seek(topicPartition, currentPosition - trendingData.size());
        }
        return trendingData;
    }

    public boolean clearProcessedData(){
        return processedTrendConsumer.poll(Duration.ofMillis(100)).count() > 0;
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
