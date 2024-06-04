package com.chs.controller;

import com.chs.module.CartItems;
import com.chs.module.Product;
import com.chs.service.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("cartItems")
public class CartController {
    @Autowired
    private CartItemsService cartItemsService;
    @Autowired
    private RestTemplate restTemplate;
    private DiscoveryClient discoveryClient;

    @Autowired
    public CartController(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/getProductAndStore/{userId}/{productId}/{qnt}")
    public CartItems getProductAndStore(@PathVariable String userId, @PathVariable String productId, @PathVariable Integer qnt) {
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(
                "http://PRODUCT-MODULE/products/getDataById/" + productId,
                Product.class
        );
        Product product = responseEntity.getBody();

        return cartItemsService.addToCart(userId, product, qnt);
    }


    @GetMapping("/getAllItems")
    public List<CartItems> getCartItems() {

        return cartItemsService.getCartItems();
    }
    @GetMapping("/getDataById/{id}")
    public ResponseEntity<?> getDataById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.cartItemsService.getuserProductById(id));
    }

    @GetMapping("/totalPrice")
    public double getCartTotalPrice(@RequestParam String userId) {

        return cartItemsService.getTotalPrice(userId);
    }

}


