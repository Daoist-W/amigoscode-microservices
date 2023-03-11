package com.isikodon.notification.rabbitmq;


//@Component
public class RabbitMQConfig {

//    @Value("${rabbitmq.exchanges.internal}")
//    private String internalExchange;
//
//    @Value("${rabbitmq.queues.notification}")
//    private String notificationQueue;
//
//    @Value("${rabbitmq.routing-keys.internal-notification}")
//    private String internalNotificationRoutingKey;
//
//    @Bean
//    public TopicExchange internalTopicExchange(){
//        return new TopicExchange(internalExchange);
//    }
//
//    @Bean
//    public Queue notificationQueue(){
//        return new Queue(notificationQueue);
//    }
//
//    @Bean
//    public Binding internalToNotificationBinding(){
//        return BindingBuilder
//                .bind(notificationQueue())
//                .to(internalTopicExchange())
//                .with(internalNotificationRoutingKey);
//    }
//
//    public String getInternalExchange() {
//        return internalExchange;
//    }
//
//    public String getNotificationQueue() {
//        return notificationQueue;
//    }
//
//    public String getInternalNotificationRoutingKey() {
//        return internalNotificationRoutingKey;
//    }
}
