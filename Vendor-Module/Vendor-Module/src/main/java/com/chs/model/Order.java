package com.chs.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Order {
    @Id
    @NotNull
    public String orderId;
    private String customerId;

    @NotEmpty(message = "Enter order Number")
    public String orderNumber;
    @NotEmpty(message = "Enter shipping address")
    public String shippingAddress;
    @NotNull(message = "Enter Order Date")
    private LocalDateTime orderDate;
    @NotNull(message = "Enter total Amount")
    private Integer totalAmount;
    @NotNull(message = "Enter payment method")
    private String paymentMethod;

    public Order(String customerId, String orderNumber, String shippingAddress, LocalDateTime orderDate , Integer totalAmount, String paymentMethod) {
        this.customerId = customerId;
        this.orderNumber = orderNumber;
        this.shippingAddress = shippingAddress;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;


    }
    public Order(){

    }
}
