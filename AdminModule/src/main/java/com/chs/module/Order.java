package com.chs.module;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "orders")
public class Order {
    @NotNull
    public String orderId;
    private String customerId;
//    @NotEmpty(message = "Enter product")
//    public String product;
    @NotEmpty(message = "Enter order Number")
    public String orderNumber;
    @NotEmpty(message = "Enter shipping address")
    public String shippingAddress;
//    @NotNull(message = "Enter Order Date")
//    private LocalDateTime orderDate;
    @NotNull(message = "Enter total Amount")
    private Integer totalAmount;
    @NotNull(message = "Enter payment method")
    private String paymentMethod;
    private List<Product> productList = new ArrayList<>();

    // Constructor

}
