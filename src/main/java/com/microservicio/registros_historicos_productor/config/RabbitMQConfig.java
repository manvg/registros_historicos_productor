package com.microservicio.registros_historicos_productor.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.core.*;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.exchange.alertas}")
    private String alertasExchange;

    @Value("${rabbitmq.queue.registros_historicos}")
    private String registrosHistoricosQueue;

    @Value("${rabbitmq.routingkey.registros_historicos}")
    private String registrosHistoricosRoutingKey;


    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(alertasExchange);
    }

    @Bean
    public Queue registrosHistoricosQueue() {
        return new Queue(registrosHistoricosQueue, true);
    }
    
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
