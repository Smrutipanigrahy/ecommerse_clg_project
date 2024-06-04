package com.chs.service;

import com.chs.module.Product;
import com.chs.module.Review;
import com.chs.module.UserRegistration;
import com.chs.module.VendorRegistration;

import java.util.List;

public interface AdminService {
    List<Product> saveProductsFromExternalService(List<Product> productList);
    List<UserRegistration> saveUserDataService(List<UserRegistration> productList);
    List<Review> storeReviewData(List<Review> reviewList);
    List<Review> getAllReviews();
    Review getReviewById(String id);

    List<VendorRegistration> saveVendorDataService(List<VendorRegistration> vendorList);
}
