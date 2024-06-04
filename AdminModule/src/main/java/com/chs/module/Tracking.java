package com.chs.module;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Tracking  {

    private String Tracking_id;
    public String Estimated_Delivery_Time;
    public String Shipping_By;
    public String Status;
    private List<Order> trackingList = new ArrayList<>();




}

