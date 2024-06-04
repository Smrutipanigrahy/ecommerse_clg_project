package com.chs.repository;

import com.chs.module.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;


@EnableMongoRepositories
public interface OrderRepo extends MongoRepository<Order,String> {

}
