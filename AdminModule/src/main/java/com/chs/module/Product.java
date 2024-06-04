package com.chs.module;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "ProductList")
public class Product {

    private String productId;
    private String main_category;
    private String sub_category;
    private String product_name;
    private String brand;
    private Double price;
    private Integer quantity;
}
