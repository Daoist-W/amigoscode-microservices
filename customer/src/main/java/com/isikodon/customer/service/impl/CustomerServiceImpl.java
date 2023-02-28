package com.isikodon.customer.service.impl;

import com.isikodon.customer.entity.CustomerEntity;
import com.isikodon.customer.model.CustomerRegistrationRequest;
import com.isikodon.customer.model.FraudCheckResponse;
import com.isikodon.customer.repository.CustomerRepository;
import com.isikodon.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;

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
        var fraudCheckresponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customerEntity.getId());

        if(fraudCheckresponse != null && fraudCheckresponse.isFraudulent()){
            throw new IllegalStateException("fraudulent");
        }
        // todo: send notification
    }
}
