package com.chs.controller;

import com.chs.module.ShippingAddress;
import com.chs.service.impl.ShippingAddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shipping")
public class ShippingAddressController {
    @Autowired
    private ShippingAddressServiceImpl shippingAddressService;
    @PostMapping("/customerAddress")
    public ShippingAddress createShippingAddress(@RequestBody ShippingAddress shippingAddress) {
        return shippingAddressService.createShippingAddress(shippingAddress);
    }

    @GetMapping("/customer")
    public List<ShippingAddress> getShippingAddress(){
        return this.shippingAddressService.getShippingAddress();
    }

}
