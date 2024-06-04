package com.chs.service;

import com.chs.model.Review;
import com.chs.model.VendorReview;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VendorReviewService {

    void storeReviewData(Review userDataArray);
    Review getReviewById(String id);
    List<VendorReview> getAllReviews();
    boolean deleteReviewById(String id);
}

