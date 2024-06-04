package com.chs.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "Tracking" )
public class Tracking {
//    @Id
    private String Tracking_id;
    public String Estimated_Delivery_Time;
    public String Shipping_By;
    public String Status;
    private List<Order> trackingList = new ArrayList<>();

}
