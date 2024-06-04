package com.chs.module;

import lombok.Data;

@Data
public class ShippingAddress {
    private String id;
    private String street;
    private String city;
    private String state;
    private String zipcode;
}
