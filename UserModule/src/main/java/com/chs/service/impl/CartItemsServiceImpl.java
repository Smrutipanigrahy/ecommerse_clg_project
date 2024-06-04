package com.chs.service.impl;

import com.chs.module.CartItems;
import com.chs.module.Product;
import com.chs.module.UserRegistration;
import com.chs.repository.CartItemsRepo;
import com.chs.service.CartItemsService;
import com.chs.service.UserRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemsServiceImpl implements CartItemsService {
    @Autowired
    private CartItemsRepo cartItemsRepo;
    @Autowired
    private UserRegService userRegService;

    @Override
    public CartItems addToCart(String userId, Product product, Integer quantity) {
        // Check if the user's cart already exists
        UserRegistration user = userRegService.getUserById(userId);

        Product convertedProduct = convertToDataEntity(product);
        if(user!= null && product != null){
            CartItems userCart =  new CartItems();
            userCart.setUserId(userId);
            userCart.setUserName(user.getUserName());
            userCart.getProductList().add(convertedProduct);
            userCart.setQant(quantity);
            userCart.setTotalPrice(product.getPrice()*quantity);

            return this.cartItemsRepo.save(userCart);
        }
        return null;
    }

    private Product convertToDataEntity(Product productData) {
        Product dataEntity = new Product();

        dataEntity.setProductId(productData.getProductId());
        dataEntity.setProduct_name(productData.getProduct_name());
        dataEntity.setPrice(productData.getPrice());
        dataEntity.setQuantity(productData.getQuantity());
        return dataEntity;
    }

    @Override
    public List<CartItems> getCartItems() {

        return this.cartItemsRepo.findAll();
    }

    @Override
    public double getTotalPrice(String userId) {
        List<CartItems> cartItems = cartItemsRepo.findByUserId(userId);

        double totalPrice = 0.0;

        for (CartItems cartItem : cartItems) {
            totalPrice += cartItem.getTotalPrice();
        }
        return totalPrice;
    }

    @Override
    public CartItems getuserProductById(String userid) {
        Optional<CartItems> product = this.cartItemsRepo.findById(userid);
        if(product.isPresent())
            return product.get();
        else
            throw new IllegalStateException("Record not found with id : " + userid);
    }
}




