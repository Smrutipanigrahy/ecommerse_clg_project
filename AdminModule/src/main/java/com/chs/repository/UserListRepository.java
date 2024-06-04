package com.chs.repository;

import com.chs.module.UserRegistration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserListRepository extends MongoRepository<UserRegistration,String> {
}
