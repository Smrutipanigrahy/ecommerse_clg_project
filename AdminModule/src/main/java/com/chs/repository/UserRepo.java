package com.chs.repository;



import com.chs.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User,String> {
    public Optional<User> findByEmailId(String emailId);
}
