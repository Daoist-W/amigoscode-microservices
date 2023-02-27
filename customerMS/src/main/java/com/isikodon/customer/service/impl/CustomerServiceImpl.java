package com.isikodon.customer.service.impl;

import com.isikodon.customer.entity.CustomerEntity;
import com.isikodon.customer.model.CustomerRegistrationRequest;
import com.isikodon.customer.repository.CustomerRepository;
import com.isikodon.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void register(CustomerRegistrationRequest customerRegistrationRequest) {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .firstName(customerRegistrationRequest.getFirstName())
                .lastName(customerRegistrationRequest.getLastName())
                .email(customerRegistrationRequest.getEmail())
                .build();

        // todo: check if email valid
        // todo: check if email not taken
        // todo: store customerEntity in db
        customerRepository.save(customerEntity);
    }
}
