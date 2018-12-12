package com.zemoso.kafkasample.configuration;

import com.zemoso.kafkasample.consumer.TrendConsumer;
import com.zemoso.kafkasample.pojos.TrendingData;
import com.zemoso.kafkasample.utils.Constants;
import com.zemoso.kafkasample.utils.KafkaTrendDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableKafka
public class TrendConsumerConfig {

    @Value("${kafka.raw.bootstrap-servers}")
    private String rawDataServer;

    @Value("${kafka.topic.raw.trending}")
    private String bootstrapTrendingTopic;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        // list of host:port pairs used for establishing the initial connections to the Kafka cluster
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, rawDataServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaTrendDeserializer.class);
        // allows a pool of processes to divide the work of consuming and processing records
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "trending");
        // automatically reset the offset to the earliest offset
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return props;
    }

    @Bean(name = Constants.RAW_TREND_CONSUMER)
    public KafkaConsumer<String, TrendingData> rawTrendConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, rawDataServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaTrendDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "trending");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        KafkaConsumer<String, TrendingData> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singleton(bootstrapTrendingTopic));
        return consumer;
    }

    @Bean
    public ConsumerFactory<String, TrendingData> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, TrendingData>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TrendingData> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public TrendConsumer trendConsumer(){
        return new TrendConsumer();
    }

}
