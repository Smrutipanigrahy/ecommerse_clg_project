package com.chs.module;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "cart_items")
public class CartItems extends UserRegistration{

    private Integer qant;
    private Double totalPrice;
    private List<Product> productList = new ArrayList<>();

}




