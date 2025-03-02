package com.chs.module;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection  = "Wishlistdetail")
public class Wishlist  {

    private UserRegistration userRegistration;
    private List<Product> productList = new ArrayList<>();
}
