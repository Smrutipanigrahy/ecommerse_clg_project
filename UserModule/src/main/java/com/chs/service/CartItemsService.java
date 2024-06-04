package com.chs.service;

import com.chs.module.CartItems;
import com.chs.module.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartItemsService {

    CartItems addToCart(String userId, Product product, Integer quantity);

    public List<CartItems> getCartItems();

    double getTotalPrice(String userId);
    CartItems getuserProductById(String userid);

}


