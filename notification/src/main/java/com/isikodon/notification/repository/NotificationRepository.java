package com.isikodon.notification.repository;

import com.isikodon.notification.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
}
