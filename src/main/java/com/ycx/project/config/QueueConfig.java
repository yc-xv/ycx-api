package com.ycx.project.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueueConfig {
    public static final String ORDER_DELAY_QUEUE = "order.delay.queue";

    // 死信交换机
    public static final String ORDER_DLX_EXCHANGE = "order.dlx.exchange";

    // 真正消费队列
    public static final String ORDER_QUEUE = "order.queue";

    public static final String ROUTING_KEY = "order.key";
    //死信交换机
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(ORDER_DLX_EXCHANGE);
    }
    //真正队列
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE, true);
    }
    //绑定
    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue())
                .to(dlxExchange())
                .with(ROUTING_KEY);
    }
    //延迟队列
    @Bean
    public Queue delayQueue() {

        Map<String, Object> args = new HashMap<>();

        // 设置死信交换机
        args.put("x-dead-letter-exchange", ORDER_DLX_EXCHANGE);

        // 设置死信 routing key
        args.put("x-dead-letter-routing-key", ROUTING_KEY);

        // 设置 TTL（30分钟）
        args.put("x-message-ttl", 3 * 1000);

        return new Queue(ORDER_DELAY_QUEUE, true, false, false, args);
    }

}
