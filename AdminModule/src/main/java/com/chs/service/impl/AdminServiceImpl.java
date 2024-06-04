package com.chs.service.impl;

import com.chs.module.Product;
import com.chs.module.Review;
import com.chs.module.UserRegistration;
import com.chs.module.VendorRegistration;
import com.chs.repository.ProductRepository;
import com.chs.repository.ReviewRepository;
import com.chs.repository.UserListRepository;
import com.chs.repository.VendorListRepo;
import com.chs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserListRepository userListRepository;
    @Autowired
    private VendorListRepo vendorListRepo;
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Product> saveProductsFromExternalService(List<Product> productList) {
        List<Product> productEntities = new ArrayList<>();
        for (Product product : productList) {
            Product entity = new Product();
            entity.setMain_category(product.getMain_category());
            entity.setSub_category(product.getSub_category());
            entity.setProduct_name(product.getProduct_name());
            entity.setPrice(product.getPrice());
            entity.setBrand(product.getBrand());
            entity.setQuantity(product.getQuantity());
            // Map other fields
            productEntities.add(entity);
        }
        return productRepository.saveAll(productEntities);
    }
    @Override
    public List<UserRegistration> saveUserDataService(List<UserRegistration> userList) {
        List<UserRegistration> userEntities = new ArrayList<>();
        for (UserRegistration user : userList) {
            UserRegistration entity = new UserRegistration();
            entity.setUserName(user.getUserName());
            entity.setMobileNo(user.getMobileNo());
            entity.setEmailId(user.getEmailId());
            entity.setPassword(user.getPassword());
            // Map other fields
            userEntities.add(entity);
        }
        return userListRepository.saveAll(userEntities);
    }

    @Override
    public List<Review> storeReviewData(List<Review> reviewList) {
        List<Review> ReviewEntities = new ArrayList<>();
        for (Review review : reviewList) {
            Review entity = new Review();
            entity.setId(review.getId());
            entity.setName(review.getName());
            entity.setRating(review.getRating());
            entity.setFeedback(review.getFeedback());
            // Map other fields
            ReviewEntities.add(entity);
        }
        return reviewRepository.saveAll(ReviewEntities);
    }

    @Override
    public List<Review> getAllReviews() {
       return reviewRepository.findAll();
    }

    @Override
    public Review getReviewById(String id) {
        Optional<Review> product = this.reviewRepository.findById(id);
        if(product.isPresent())
            return product.get();
        else
            throw new IllegalStateException("Record not found with id : " + id);
    }

    @Override
    public List<VendorRegistration> saveVendorDataService(List<VendorRegistration> vendorList) {
        List<VendorRegistration> vendorEntities = new ArrayList<>();
        for (VendorRegistration vendor : vendorList) {
            VendorRegistration entity = new VendorRegistration();
            entity.setVendorId(vendor.getVendorId());
            entity.setVendorName(vendor.getVendorName());
            entity.setEmailId(vendor.getEmailId());
            entity.setProductLicenceNo(vendor.getProductLicenceNo());
            entity.setProduct(vendor.getProduct());
            // Map other fields
            vendorEntities.add(entity);
        }
        return vendorListRepo.saveAll(vendorEntities);
    }


}
