package com.chs.service;

import com.chs.module.Order;
import com.chs.module.Product;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void storeProductFororder(String orderId,Product product);
    Order createOrder(Order order);
    List<Order> getAllOrders();
    Optional<Order> trackOrderHistory(String orderId);
    void deleteOrder(String orderId);

}
