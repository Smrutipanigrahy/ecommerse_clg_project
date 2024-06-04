package com.chs.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "addProducts")
public class VendorModule {
    @Id
    @NonNull
    private String productId;
    @NotEmpty(message = "Enter Main Category")
    private String main_category;
    @NotEmpty(message = "Enter Sub Category")
    private String sub_category;
    @NotEmpty(message = "Enter Product name")
	private String product_name;
    @NotEmpty(message = "Enter Product Brand")
    private String brand;
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

    public VendorModule() {
    }

    public VendorModule(@NonNull String productId, String main_category, String sub_category, String product_name, @NotNull(message = "Enter Product Price") Double price, @NotNull(message = "Enter Product Quantity") Integer quantity, String warrenty, String description, String specification) {
        this.productId = productId;
        this.main_category = main_category;
        this.sub_category = sub_category;
        this.product_name = product_name;
        this.price = price;
        this.quantity = quantity;
        this.warrenty = warrenty;
        this.description = description;
        this.specification = specification;
    }
}
