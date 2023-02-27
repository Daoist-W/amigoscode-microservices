package com.isikodon.fraud.service.impl;

import com.isikodon.fraud.entity.FraudCheckHistory;
import com.isikodon.fraud.repository.FraudCheckHistoryRepository;
import com.isikodon.fraud.service.FraudCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("fraudCheckService")
public class FraudCheckServiceImp implements FraudCheckService {

    @Autowired
    private FraudCheckHistoryRepository fraudRepository;

    @Override
    public boolean isFraudulentCustomer(Integer customerId) {
        fraudRepository.save(
                FraudCheckHistory.builder()
                        .isFraudster(false)
                        .customerId(customerId)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
