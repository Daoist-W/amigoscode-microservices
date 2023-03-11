package com.isikodon.notification.kafka;

import com.isikodon.clients.notification.NotificationRequest;
import com.isikodon.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaNotificationConsumer {

    public final NotificationService notificationService;

    @KafkaListener(
            topics = "notification",
            groupId = "group1",
            containerFactory = "factory" // must be added or will default to in-built factory
    )
    public void consume(NotificationRequest notificationRequest){
        log.info("Consumed {} from topic", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
