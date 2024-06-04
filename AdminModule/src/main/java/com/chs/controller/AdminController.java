package com.chs.controller;

import com.chs.entities.User;
import com.chs.module.*;
import com.chs.service.AdminService;
import com.chs.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;


    ////Poduct datas from Product Module
    @PostMapping("/add-product")
    public ResponseEntity<String> addProduct(@Valid @RequestBody VendorModule vendorModule) {

        String vendorServiceUrl = "http://localhost:9090/vendor/addproduct";

        ResponseEntity<?> response = restTemplate.postForEntity(vendorServiceUrl, vendorModule, Response.class);

        return ResponseEntity.ok("Product added successfully from Admin.");
    }
    @GetMapping("/productList")
    public List<Product> getProductList() {
        String productServiceUrl = "http://localhost:8082/";
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                productServiceUrl + "/products/getAllData",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {}
        );
        List<Product> productList = response.getBody();

        return adminService.saveProductsFromExternalService(productList);
    }
    @GetMapping("/productDetails/{productId}")
    public Product getProductDetails(@PathVariable String productId) {
        String productModuleUrl = "http://localhost:8082/";
        ResponseEntity<Product> response = restTemplate.getForEntity(
                productModuleUrl + "/products/getDataById/" + productId,
                Product.class
        );
        return response.getBody();
    }



    ////Adding User details from user module
    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@Valid @RequestBody UserRegistration userRegistration) {

        String userServiceUrl = "http://localhost:7070/userRegistration/addDetails";

        ResponseEntity<?> response = restTemplate.postForEntity(userServiceUrl, userRegistration, Response.class);

        return ResponseEntity.ok("User added successfully from Admin.");
    }
    //Updating user Details
    @PutMapping("/update-user/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @Valid @RequestBody UserRegistration userRegistration) {

        String userServiceUrl = "http://localhost:7070/userRegistration/update/" + userId;

        HttpEntity<UserRegistration> requestEntity = new HttpEntity<>(userRegistration);
        ResponseEntity<?> response = restTemplate.exchange(userServiceUrl, HttpMethod.PUT, requestEntity, Response.class);

        return ResponseEntity.ok("User updated successfully from Admin.");
    }

    //Showing all user data from user module
    @GetMapping("/userList")
    public List<UserRegistration> getUserList() {
        // Make a REST API call to the Product Service to fetch product data
        String userServiceUrl = "http://localhost:7070/";
        ResponseEntity<List<UserRegistration>> response = restTemplate.exchange(
                userServiceUrl + "/userRegistration/getAllData",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserRegistration>>() {}
        );
        List<UserRegistration> userList = response.getBody();
        return adminService.saveUserDataService(userList);
    }
    @GetMapping("/userDetails/{userId}")
    public UserRegistration getUserDetails(@PathVariable String userId) {
        String productModuleUrl = "http://localhost:7070/";
        ResponseEntity<UserRegistration> response = restTemplate.getForEntity(
                productModuleUrl + "/userRegistration/getDataById/" + userId,
                UserRegistration.class
        );
        return response.getBody();
    }


    ////Shows all the data of the login table in admin module
    @GetMapping("/getAllData")
    public ResponseEntity<List<User>> getAllUserDetails(){
        return ResponseEntity.ok().body(this.userService.getUsers());
    }
    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }



    ////Review List from User Review
    @GetMapping("/getReview")
    public List<Review> getReview() {
        String productModuleUrl = "http://localhost:7070/";
        ResponseEntity<List<Review>> response = restTemplate.exchange(
                productModuleUrl + "/user-review/allReviews",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {}
        );
        List<Review> userReview = response.getBody();

        return adminService.storeReviewData(userReview);
    }
    @GetMapping("/getReviewById/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable String id) {
        Review review =  adminService.getReviewById(id);
        if (review != null) {
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/AllReviews")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews =  adminService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }
    @GetMapping("/{id}/feedback")
    public ResponseEntity<String> getFeedbackById(@PathVariable String id) {
        Review review =  adminService.getReviewById(id);

        if (review != null) {
            return ResponseEntity.ok(review.getFeedback());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/feedbacks")
    public ResponseEntity<List<String>> getAllFeedbacks() {
        List<Review> reviews =  adminService.getAllReviews();

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


    ////adding Vendor Details to Admin module
    @PostMapping("/add-vendor")
    public ResponseEntity<String> addVendorDetails(@Valid @RequestBody VendorRegistration vendorRegistration) {

        String vendorServiceUrl = "http://localhost:9090/vendorRegistration/addDetails";

        ResponseEntity<?> response = restTemplate.postForEntity(vendorServiceUrl, vendorRegistration, Response.class);

        return ResponseEntity.ok("Vendor added successfully from Admin.");
    }
    //Updating Vendor Details
    @PutMapping("/update-vendor/{vendorId}")
    public ResponseEntity<String> updateVendor(@PathVariable String vendorId, @Valid @RequestBody VendorRegistration vendorRegistration) {

        String vendorServiceUrl = "http://localhost:9090/vendorRegistration/update/" + vendorId;

        HttpEntity<VendorRegistration> requestEntity = new HttpEntity<>(vendorRegistration);
        ResponseEntity<?> response = restTemplate.exchange(vendorServiceUrl, HttpMethod.PUT, requestEntity, Response.class);

        return ResponseEntity.ok("Vendor updated successfully from Admin.");
    }
    @GetMapping("/vendorList")
    public List<VendorRegistration> getVendorList() {
        // Make a REST API call to the Product Service to fetch product data
        String userServiceUrl = "http://localhost:9090/";
        ResponseEntity<List<VendorRegistration>> response = restTemplate.exchange(
                userServiceUrl + "/vendorRegistration/vendorDetails",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<VendorRegistration>>() {}
        );
        List<VendorRegistration> userList = response.getBody();
        return adminService.saveVendorDataService(userList);
    }





}
