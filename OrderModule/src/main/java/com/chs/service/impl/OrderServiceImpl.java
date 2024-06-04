package com.chs.service.impl;

import com.chs.module.Order;
import com.chs.module.Product;
import com.chs.repository.OrderRepo;
import com.chs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    public void OrderService(OrderRepo orderRepo) {

        this.orderRepo = orderRepo;
    }


    @Override
    public void storeProductFororder(String orderId, Product product) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new IllegalStateException("order not found with ID: " + orderId));


        if (order != null) {
            Product convertedProduct = convertToDataEntity(product);
            order.getProductList().add(convertedProduct);
            orderRepo.save(order);
        } else {
            throw new IllegalStateException("tracking_id not found with ID: " +  orderId);
        }
    }
    private Product convertToDataEntity(Product productData) {
        Product dataEntity = new Product();

        dataEntity.setProductId(productData.getProductId());
        dataEntity.setProduct_name(productData.getProduct_name());
        dataEntity.setQuantity(productData.getQuantity());
        dataEntity.setPrice(productData.getPrice());
        return dataEntity;
    }

    public Order createOrder(Order order) {
        return this.orderRepo.save(order);

    }
    public List<Order> getAllOrders() {
        return this.orderRepo.findAll();
    }

    public Optional<Order> trackOrderHistory(String orderId) {
        return this.orderRepo.findById(orderId);
    }

    public void deleteOrder(String orderId) {
        Optional<Order> productDb = this.orderRepo.findById(orderId);
        if (productDb.isPresent())
            this.orderRepo.delete(productDb.get());
        else
            throw new IllegalStateException("Record not found with id : " + orderId);
    }




}