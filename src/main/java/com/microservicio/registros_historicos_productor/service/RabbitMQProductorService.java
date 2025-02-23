package com.microservicio.registros_historicos_productor.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.microservicio.registros_historicos_productor.model.SignosVitales;

@Service
public class RabbitMQProductorService {
private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.alertas}")
    private String alertasExchange;

    @Value("${rabbitmq.routingkey.registros_historicos}")
    private String registrosHistoricosRoutingKey;

    public RabbitMQProductorService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarAlertaCritica(SignosVitales senales) {
        rabbitTemplate.convertAndSend(alertasExchange, registrosHistoricosRoutingKey, senales);
        System.out.println("Registro histórico enviado a RabbitMQ -> Exchange: " + alertasExchange + ", Routing Key: " + registrosHistoricosRoutingKey);
    }
}
