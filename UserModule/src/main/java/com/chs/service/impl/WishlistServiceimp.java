package com.chs.service.impl;

import com.chs.module.Product;
import com.chs.module.UserRegistration;
import com.chs.module.Wishlist;
import com.chs.repository.WishlistRepository;
import com.chs.service.UserRegService;
import com.chs.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WishlistServiceimp implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private UserRegService userRegService;
    @Override
    public List<Wishlist> wishlistAll() {
        return wishlistRepository.findAll();
    }

    @Override
    public Wishlist storeProductInWishlist(String userId,Product product) {
        // Assuming you have a User entity with a field to store products
        UserRegistration user = userRegService.getUserById(userId);

        if(user!= null && product != null){
            Wishlist wishlist = new Wishlist();
            UserRegistration userRegistration = new UserRegistration();
            userRegistration.setUserId(userId);
            userRegistration.setUserName(user.getUserName());

            Product convertedProduct = convertToDataEntity(product);
            wishlist.getProductList().add(convertedProduct);

            return this.wishlistRepository.save(wishlist);
        }
        return null;
    }
    private Product convertToDataEntity(Product productData) {
        Product dataEntity = new Product();

        dataEntity.setProductId(productData.getProductId());
        dataEntity.setProduct_name(productData.getProduct_name());
        dataEntity.setPrice(productData.getPrice());
        dataEntity.setQuantity(productData.getQuantity());
        dataEntity.setWarrenty(productData.getWarrenty());
        dataEntity.setDescription(productData.getDescription());
        dataEntity.setSpecification(productData.getSpecification());

        return dataEntity;
    }

    @Override
    public Wishlist addUserdata(Wishlist wishlist) {
        return this.wishlistRepository.save(wishlist);
    }


}