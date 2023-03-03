package com.isikodon.customer.service.impl;

import com.isikodon.amqp.RabbitMQMessageProducer;
import com.isikodon.clients.fraud.FraudCheckResponse;
import com.isikodon.clients.fraud.FraudClient;
import com.isikodon.clients.notification.NotificationClient;
import com.isikodon.clients.notification.NotificationRequest;
import com.isikodon.customer.entity.CustomerEntity;
import com.isikodon.customer.model.CustomerRegistrationRequest;
import com.isikodon.customer.repository.CustomerRepository;
import com.isikodon.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("customerService")
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer producer;

    @Override
    public void register(CustomerRegistrationRequest customerRegistrationRequest) {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .firstName(customerRegistrationRequest.getFirstName())
                .lastName(customerRegistrationRequest.getLastName())
                .email(customerRegistrationRequest.getEmail())
                .build();

        // todo: check if email valid
        // todo: check if email not taken

        customerRepository.saveAndFlush(customerEntity);

        // check if fraudster
        FraudCheckResponse fraudCheckresponse = fraudClient.isFraudster(customerEntity.getId());

        if(fraudCheckresponse != null && fraudCheckresponse.isFraudulent()){
            throw new IllegalStateException("fraudulent");
        }

        // send notification
        NotificationRequest request = new NotificationRequest();
        request.setToCustomerId(customerEntity.getId());
        request.setToCustomerEmail(customerEntity.getEmail());
        request.setMessage("Hello world");

        // todo: centralise source of exchange and routingKey
        producer.publish(request, "internal.exchange", "internal.notification.routing-key");

    }
}
