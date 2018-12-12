package com.zemoso.kafkasample.configuration;

import com.zemoso.kafkasample.pojos.TrendingData;
import com.zemoso.kafkasample.producer.TrendProducer;
import com.zemoso.kafkasample.utils.Constants;
import com.zemoso.kafkasample.utils.KafkaTrendSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
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

    private Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        // list of host:port pairs used for establishing the initial connections to the Kakfa cluster
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, rawDataServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaTrendSerializer.class);
        return props;
    }

    private ProducerFactory<String, TrendingData> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean(name = Constants.RAW_TREND_PRODUCER)
    public KafkaTemplate<String, TrendingData> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public TrendProducer trendProducer() {
        return new TrendProducer();
    }

}
