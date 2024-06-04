package com.chs.controller;

import com.chs.module.Order;
import com.chs.module.Product;
import com.chs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("orders")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private OrderService orderService;
    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    @Autowired
    public OrderController(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/getProductAndStore/{orderId}/{productId}")
    public String gettrackingAndStore(@PathVariable String orderId, @PathVariable String productId) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("PRODUCT-MODULE");

        if (!serviceInstances.isEmpty()) {
            ServiceInstance serviceInstance = serviceInstances.get(0);
            String url = serviceInstance.getUri() + "/products/getDataById/" + productId;

            ResponseEntity<Product> responseEntity = restTemplate.getForEntity(url, Product.class);
            Product product = responseEntity.getBody();

            orderService.storeProductFororder(orderId, product);

            return "Product fetched by ID and stored for the Order";
        } else {
            return "PRODUCT-MODULE is not available";
        }
    }


    @PostMapping("/addData")
    public Order createOrder(@RequestBody Order order) {

        return orderService.createOrder(order);
    }
    @GetMapping("/allOrders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok().body(this.orderService.getAllOrders());
    }
    @GetMapping("/trackOrderHistory/{orderId}")
    public ResponseEntity<Optional<Order>> trackOrderHistory(@PathVariable String orderId){
        return ResponseEntity.ok().body(this.orderService.trackOrderHistory(orderId));
    }

    @DeleteMapping("/deleteById/{orderId}")
    public HttpStatus deleteOrder(@PathVariable String orderId) {
        this.orderService.deleteOrder(orderId);
        return HttpStatus.OK;
    }

}

