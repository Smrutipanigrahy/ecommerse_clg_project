package com.chs.service.impl;

import com.chs.model.Review;
import com.chs.model.VendorReview;
import com.chs.repo.VendorReviewRepo;
import com.chs.service.VendorReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VendorReviewImpl implements VendorReviewService {

    private final VendorReviewRepo vendorReviewRepo;

    @Autowired
    public VendorReviewImpl(VendorReviewRepo vendorReviewRepo) {
        this.vendorReviewRepo = vendorReviewRepo;
    }

    @Override
    public void storeReviewData(Review userDataArray) {
        List<VendorReview> vendorReviews = convertToVendorReviews(userDataArray);
        vendorReviewRepo.saveAll(vendorReviews);
    }

    @Override
    public Review getReviewById(String id) {
        return vendorReviewRepo.findById(id).orElse(null);
    }

    @Override
    public List<VendorReview> getAllReviews() {
        return vendorReviewRepo.findAll();
    }

    @Override
    public boolean deleteReviewById(String id) {
        try {
            vendorReviewRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private List<VendorReview> convertToVendorReviews(Review userDataArray) {
        return Stream.of(userDataArray)
                .map(this::convertToVendorReview)
                .collect(Collectors.toList());
    }

    private VendorReview convertToVendorReview(Review userData) {
        VendorReview vendorReview = new VendorReview();
        vendorReview.setId(userData.getId());
        vendorReview.setName(userData.getName());
        vendorReview.setRating(userData.getRating());
        vendorReview.setFeedback(userData.getFeedback());
        return vendorReview;
    }
}
