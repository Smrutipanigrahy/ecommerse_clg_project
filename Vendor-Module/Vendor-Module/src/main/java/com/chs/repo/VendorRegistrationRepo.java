package com.chs.repo;

import com.chs.model.VendorRegistration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VendorRegistrationRepo extends MongoRepository<VendorRegistration,String> {
}
