package com.chs.service;

import com.chs.module.Product;
import com.chs.module.Wishlist;

import java.util.List;

public interface WishlistService {
    List<Wishlist> wishlistAll();
    Wishlist storeProductInWishlist(String userid,Product product);
    Wishlist addUserdata(Wishlist wishlist);


}
