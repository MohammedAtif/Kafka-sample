package com.zemoso.kafkasample.confiuration;

import com.zemoso.kafkasample.pojos.TrendingData;
import com.zemoso.kafkasample.producer.TrendProducer;
import com.zemoso.kafkasample.producer.TrendSerializer;
import com.zemoso.kafkasample.utils.Constants;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TrendProducerConfig {

    @Value("${kafka.raw.bootstrap-servers}")
    private String rawDataServer;

    @Value("${kafka.processed.bootstrap-servers}")
    private String processedDataServer;

    private Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        // list of host:port pairs used for establishing the initial connections to the Kakfa cluster
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, rawDataServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        return props;
    }

    private ProducerFactory<String, Integer> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean(name = Constants.RAW_TREND_PRODUCER)
    public KafkaTemplate<String, Integer> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }



    private Map<String, Object> producerProcessedConfigs() {
        Map<String, Object> props = new HashMap<>();
        // list of host:port pairs used for establishing the initial connections to the Kakfa cluster
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, processedDataServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TrendSerializer.class);
        return props;
    }

    private ProducerFactory<String, TrendingData> processedProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerProcessedConfigs());
    }

    @Bean(name = Constants.PROCESSED_TREND_PRODUCER)
    public KafkaTemplate<String, TrendingData> kafkaProcessedTemplate() {
        return new KafkaTemplate<>(processedProducerFactory());
    }

    @Bean
    public TrendProducer trendProducer() {
        return new TrendProducer();
    }

}
