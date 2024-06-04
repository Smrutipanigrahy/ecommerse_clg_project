package com.chs.repository;

import com.chs.module.ShippingAddress;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface ShippingAddressRepo extends MongoRepository<ShippingAddress, String> {
}
