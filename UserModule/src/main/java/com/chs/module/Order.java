package com.chs.module;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Order {
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

}
