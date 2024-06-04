package com.chs.service;

import com.chs.model.VendorModule;

import java.util.List;

public interface VendorService {

    VendorModule addProduct(VendorModule vendorModule);
    VendorModule updateProduct(VendorModule vendorModule);
    void updateAvailabilityStatus(String productId, String availabilityStatus);
    List<VendorModule> getProducts();
    List<VendorModule> outOffStock();
    VendorModule getDataById(String id);
}
