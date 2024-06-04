package com.chs.repository;

import com.chs.module.PaymentMethod;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentMethodrepos extends MongoRepository<PaymentMethod,String> {
    List<PaymentMethod> findByUserId(String userId);
}
