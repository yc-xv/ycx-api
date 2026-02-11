package com.ycx.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        // 1. 创建 RedisTemplate
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // 2. key 序列化方式：String
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // 3. value 序列化方式：JSON
        GenericJackson2JsonRedisSerializer jsonSerializer =
                new GenericJackson2JsonRedisSerializer();

        // 4. 设置 key / value 序列化
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jsonSerializer);

        // 5. 设置 Hash 的 key / value 序列化
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(jsonSerializer);

        // 6. 初始化
        template.afterPropertiesSet();

        return template;
    }
}

