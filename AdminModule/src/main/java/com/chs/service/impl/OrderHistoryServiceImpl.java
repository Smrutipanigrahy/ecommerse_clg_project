package com.chs.service.impl;

import com.chs.module.Order;
import com.chs.module.OrderHistory;
import com.chs.repository.OrderHistoryRepo;
import com.chs.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderHistoryServiceImpl implements OrderDetailsService {
    @Autowired
    private OrderHistoryRepo detailsRepo;


    @Override
    public void storeOrderData(List<Order> orderDataList) {
        List<OrderHistory> orderDetails = convertToDataEntities(orderDataList);
        detailsRepo.saveAll(orderDetails);
    }


    //Method to convert the Data into list
    private List<OrderHistory> convertToDataEntities(List<Order> orderList) {
        return orderList.stream()
                .map(this::convertToDataEntity)
                .collect(Collectors.toList());
    }


    //Method to convert Order Module data into Order Details data and storing in dataEntity
    private OrderHistory convertToDataEntity(Order orderData) {
        Order dataEntity = new Order();

        dataEntity.setOrderId(orderData.getOrderId());
        dataEntity.setCustomerId(orderData.getCustomerId());
        dataEntity.setShippingAddress(orderData.getShippingAddress());
        dataEntity.setTotalAmount(orderData.getTotalAmount());
        dataEntity.setPaymentMethod(orderData.getPaymentMethod());

        return (OrderHistory) dataEntity;
    }


    @Override
        public OrderHistory getOrderById(String orderId) {
            Optional<OrderHistory> orderDetails = this.detailsRepo.findById(orderId);
            if(orderDetails.isPresent())
                return orderDetails.get();
            else
                throw new IllegalStateException("Record not found with id : " + orderId);
        }


        @Override
    public List<OrderHistory> getAllOrderDetails() {
        return this.detailsRepo.findAll();
    }

}