package com.chs.controller;

import com.chs.module.Order;
import com.chs.module.OrderHistory;
import com.chs.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/adminOrder")
public class OrderController {
    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/orderHistory")
    public List<OrderHistory> getOrderList() {
        // Make a REST API call to the Product Service to fetch product data
        String orderServiceUrl = "http://localhost:7071";
        ResponseEntity<List<OrderHistory>> response = restTemplate.exchange(
                orderServiceUrl + "/orders/allOrders",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderHistory>>() {}
        );
        return response.getBody();
    }
    @GetMapping("/orderDetails/{orderId}")
    public Order getUserDetails(@PathVariable String orderId) {
        String orderModuleUrl = "http://localhost:7071/";
        ResponseEntity<Order> response = restTemplate.getForEntity(
                orderModuleUrl + "/orders/trackOrderHistory/" + orderId,
                Order.class
        );
        return response.getBody();
    }
}
