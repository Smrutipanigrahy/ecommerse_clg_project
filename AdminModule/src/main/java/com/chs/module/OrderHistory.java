package com.chs.module;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "orderHistory")
public class OrderHistory extends Order{
    private List<Order> orderList = new ArrayList<>();
}
