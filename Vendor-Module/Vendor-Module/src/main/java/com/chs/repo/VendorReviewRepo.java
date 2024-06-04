package com.chs.repo;

import com.chs.model.VendorReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface VendorReviewRepo extends MongoRepository<VendorReview, String> {


}