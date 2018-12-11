package com.zemoso.kafkasample.configuration;

import com.zemoso.kafkasample.pojos.TrendingData;
import com.zemoso.kafkasample.redis.TrendRedisSerializer;
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

    @Bean
    public TrendRedisSerializer getRedisSerialiser(){
        return new TrendRedisSerializer();
    }

    @Bean
    public TrendDataRepository getTrendDataRepository(RedisTemplate<String, TrendingData> redisTemplate){
        return new TrendDataRedisImpl(redisTemplate);
    }

}
