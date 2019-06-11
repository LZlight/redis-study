package com.light.study.redis.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author light
 * @Date 2019-01-21 23:52
 */
@Configuration
@EnableAutoConfiguration
public class RedisConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    protected JedisPoolConfig getJediPoolConfig(){
        return new JedisPoolConfig();
    }

    @Bean("connectionFactory")
    @ConfigurationProperties(prefix = "spring.redis")
    public RedisConnectionFactory connectionFactory(JedisPoolConfig poolConfig) {
        return new JedisConnectionFactory(poolConfig);
    }

    @Bean
    public RedisTemplate redisTemplate(@Qualifier("connectionFactory") RedisConnectionFactory factory) {
        RedisTemplate<String, byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }


    @Bean(name="userStringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }
}
