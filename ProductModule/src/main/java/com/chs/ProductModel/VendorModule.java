package com.chs.ProductModel;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class VendorModule {
    private String productId;
    @NotEmpty(message = "Enter Main Category")
    private String main_category;
    @NotEmpty(message = "Enter Sub Category")
    private String sub_category;
    @NotEmpty(message = "Enter Product name")
    private String product_name;
    @NotNull(message = "Enter Product Price")
    private Double price;
    @NotNull(message = "Enter Product Quantity")
    private Integer quantity;
    @NotEmpty(message = "Enter Product Warrenty ")
    private String warrenty;
    private String description;
    @NotEmpty
    private String specification;
    private String availabilityStatus;
}
