package com.isikodon.amqp;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
/*
  This is the setup required for us to send and receive message via spring-amqp
 */
public class RabbitMQConfig {
    private final ConnectionFactory connectionFactory;

    @Bean
    /*
      This is for sending messages (serializing)
      Default implementation shown for clarity, can be configured here to suit needs
     */
    public AmqpTemplate amqpTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    /*
      This is for receiving messages (deserializing)
      Both the listener and template have the sae connection factory and message converter
      Default implementation shown for clarity, can be configured here to suit needs
     */
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(){
        var factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter());
        return factory;
    }

    @Bean
    /*
      Default implementation shown for clarity, can be configured here to suit needs
     */
    public MessageConverter messageConverter(){
        // if required, we can configure this further with a json object mapper
        // but for now we leave this with default settings
        return new Jackson2JsonMessageConverter();
    }
}
