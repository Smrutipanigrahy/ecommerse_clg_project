package com.chs.repository;


import com.chs.module.CartItems;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CartItemsRepo extends MongoRepository<CartItems, String > {
    List<CartItems> findByUserId(String userId);
}