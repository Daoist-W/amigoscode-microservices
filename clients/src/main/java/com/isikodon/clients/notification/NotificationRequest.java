package com.isikodon.clients.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    Integer toCustomerId;
    String toCustomerEmail;
    String message;
}
