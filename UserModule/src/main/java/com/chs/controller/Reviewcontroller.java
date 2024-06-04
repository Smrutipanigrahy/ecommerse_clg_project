package com.chs.controller;

import com.chs.module.Review;
import com.chs.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user-review")
public class Reviewcontroller {
    private ReviewService reviewService;


    @Autowired
    public Reviewcontroller(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/addReview")
    public ResponseEntity<String> addReview(@RequestBody Review review) {
        Review newReview = reviewService.addReview(review);

        if (newReview != null) {
            String successMessage = "Review added successfully with ID: " + newReview.getId();
            return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add the review.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable String id) {
        Review review = reviewService.getReviewById(id);
        if (review != null) {
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/allReviews")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }
    @GetMapping("/{id}/feedback")
    public ResponseEntity<String> getFeedbackById(@PathVariable String id) {
        Review review = reviewService.getReviewById(id);

        if (review != null) {
            return ResponseEntity.ok(review.getFeedback());
        } else {
            return ResponseEntity.notFound().build();
        }
    }@GetMapping("/feedbacks")
    public ResponseEntity<List<String>> getAllFeedbacks() {
        List<Review> reviews = reviewService.getAllReviews();

        // Create a list to store all the feedbacks
        List<String> feedbacks = new ArrayList<>();

        for (Review review : reviews) {
            feedbacks.add(review.getFeedback());
        }

        if (!feedbacks.isEmpty()) {
            return ResponseEntity.ok(feedbacks);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        boolean deleted = reviewService.deleteReviewById(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

