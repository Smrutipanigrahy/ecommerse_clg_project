package com.chs.repository;

import com.chs.module.Review;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ReviewRepository extends MongoRepository<Review, String> {
}

