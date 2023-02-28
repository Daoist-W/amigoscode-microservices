package com.isikodon.notification.service;

import com.isikodon.clients.notification.NotificationRequest;

public interface NotificationService {

    void send(NotificationRequest notificationRequest);
}
