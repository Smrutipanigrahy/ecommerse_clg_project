package com.chs.repository;

import com.chs.module.VendorRegistration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VendorListRepo extends MongoRepository<VendorRegistration,String> {
}
