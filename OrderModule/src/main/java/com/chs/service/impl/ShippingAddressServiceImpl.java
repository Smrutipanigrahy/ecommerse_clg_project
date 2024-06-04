package com.chs.service.impl;

import com.chs.module.ShippingAddress;
import com.chs.repository.ShippingAddressRepo;
import com.chs.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
    @Autowired
    private ShippingAddressRepo shippingAddressRepo;
    public ShippingAddress createShippingAddress(ShippingAddress shippingAddress) {
        return shippingAddressRepo.save(shippingAddress);
    }
    public List<ShippingAddress> getShippingAddress(){

        return this.shippingAddressRepo.findAll();
    }
}

