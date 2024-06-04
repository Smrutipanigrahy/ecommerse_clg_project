package com.chs.service;

import com.chs.module.Order;
import com.chs.module.OrderHistory;

import java.util.List;

public interface OrderDetailsService {
    public void storeOrderData(List<Order> orderDataList);

    List<OrderHistory> getAllOrderDetails();

    OrderHistory getOrderById(String orderId);

}
