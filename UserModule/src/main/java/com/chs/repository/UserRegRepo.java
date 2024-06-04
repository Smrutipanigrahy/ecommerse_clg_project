package com.chs.repository;

import com.chs.module.UserRegistration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRegRepo extends MongoRepository<UserRegistration,String> {
}
