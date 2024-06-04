package com.chs.repository;

import com.chs.module.OrderHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderHistoryRepo extends MongoRepository<OrderHistory, String> {

}
