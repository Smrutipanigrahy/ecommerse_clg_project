package com.chs.service;

import com.chs.module.Review;

import java.util.List;


public interface ReviewService {
    Review getReviewById(String id);
    List<Review> getAllReviews();
    Review addReview(Review review);
    boolean deleteReviewById(String id);
}
