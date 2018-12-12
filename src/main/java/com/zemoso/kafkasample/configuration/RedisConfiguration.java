package com.zemoso.kafkasample.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoso.kafkasample.pojos.TrendingData;
import com.zemoso.kafkasample.utils.TrendRedisSerializer;
import com.zemoso.kafkasample.repositories.TrendDataRepository;
import com.zemoso.kafkasample.repositories.impl.TrendDataRedisImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {

    @Bean
    JedisConnectionFactory getJedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("redis", 6379);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, TrendingData> redisTemplate(JedisConnectionFactory jedisConnectionFactory, TrendRedisSerializer trendRedisSerializer) {
        final RedisTemplate<String, TrendingData> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setValueSerializer(trendRedisSerializer);
        return template;
    }

    @Bean ObjectMapper getSerDesrObjectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public TrendRedisSerializer getRedisSerialiser(ObjectMapper objectMapper){
        return new TrendRedisSerializer(objectMapper);
    }

    @Bean
    public TrendDataRepository getTrendDataRepository(RedisTemplate<String, TrendingData> redisTemplate){
        return new TrendDataRedisImpl(redisTemplate);
    }

}
