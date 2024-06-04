package com.chs.controller;

import com.chs.model.Review;
import com.chs.model.VendorReview;
import com.chs.service.VendorReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/vendor-review")
@EnableDiscoveryClient
public class VendorReviewController {
    @Autowired
    private  VendorReviewService  vendorReviewService;
    private  RestTemplate restTemplate;
    private DiscoveryClient discoveryClient;

    @Autowired
    public VendorReviewController(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }


    @GetMapping("/getDataFromUserReview")
    public String getDataFromAnotherService() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("USER_MODULE");

        if (serviceInstances.isEmpty()) {
            return "USER-MODULE is not available";
        }

        ServiceInstance serviceInstance = serviceInstances.get(0);
        String url = serviceInstance.getUri() + "/Review/AllReviews"; // Corrected endpoint URL
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Review[]> responseEntity = restTemplate.getForEntity(url, Review[].class);
        Review[] reviewArray = responseEntity.getBody();

        if (reviewArray != null && reviewArray.length > 0) {
            for (Review review : reviewArray) {
                vendorReviewService.storeReviewData(review);
            }
            return "Data from User module has been stored in Vendor module";
        } else {
            return "No reviews found from User module.";
        }

    }
    @GetMapping("/getReviewById/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable String id) {
        Review review = vendorReviewService.getReviewById(id);
        if (review != null) {
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllReviews")
    public ResponseEntity<List<VendorReview>> getAllReviews() {
        List<VendorReview> reviews = vendorReviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }
    @GetMapping("/{id}/feedback")
    public ResponseEntity<String> getFeedbackById(@PathVariable String id) {
        Review review = vendorReviewService.getReviewById(id);

        if (review != null) {
            return ResponseEntity.ok(review.getFeedback());
        } else {
            return ResponseEntity.notFound().build();
        }
    }@GetMapping("/getAllfeedbacks")
    public ResponseEntity<List<String>> getAllFeedbacks() {
        List<VendorReview> reviews = vendorReviewService.getAllReviews();

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
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        boolean deleted = vendorReviewService.deleteReviewById(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}






