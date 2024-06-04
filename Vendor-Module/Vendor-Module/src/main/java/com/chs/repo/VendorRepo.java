package com.chs.repo;

import com.chs.model.VendorModule;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface VendorRepo extends MongoRepository<VendorModule,String> {
}
