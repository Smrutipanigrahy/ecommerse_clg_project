package com.chs.controller;
import com.chs.module.Product;

import com.chs.module.Wishlist;
import com.chs.service.WishlistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin("*")
public class WishlistController  {
    private final WishlistService wishlistService;
    private RestTemplate restTemplate;
    private DiscoveryClient discoveryClient;

    @Autowired
    public WishlistController(WishlistService wishlistService, DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.wishlistService = wishlistService;
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/getProductAndStore/{userId}/{productId}")
    public String getProductAndStore(@PathVariable String userId, @PathVariable String productId) {
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(
                "http://localhost:8082/products/getDataById/" + productId,
                Product.class
        );
        Product product = responseEntity.getBody();
        wishlistService.storeProductInWishlist(userId, product);
        return "Product fetched by ID and stored for the user";
    }

    @GetMapping("/getbyall")
    public List<Wishlist> getall (){
        return wishlistService.wishlistAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Wishlist> addUserDetails(@Valid @RequestBody Wishlist wsh){
        return ResponseEntity.ok().body(this.wishlistService.addUserdata(wsh));
    }

}


