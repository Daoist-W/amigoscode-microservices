package com.isikodon.customer.service.impl;

import com.isikodon.clients.fraud.FraudCheckResponse;
import com.isikodon.clients.fraud.FraudClient;
import com.isikodon.customer.entity.CustomerEntity;
import com.isikodon.customer.model.CustomerRegistrationRequest;
import com.isikodon.customer.repository.CustomerRepository;
import com.isikodon.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("customerService")
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;

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
        // todo: send notification
    }
}
