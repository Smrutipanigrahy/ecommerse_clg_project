package com.chs.service.impl;

import com.chs.model.VendorModule;
import com.chs.repo.VendorRepo;
import com.chs.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {
    @Autowired
    private VendorRepo vendorRepo;

    @Override
    public VendorModule addProduct(VendorModule vendorModule) {
        return this.vendorRepo.save(vendorModule);
    }

    @Override
    public VendorModule updateProduct(VendorModule vendorModule) {
        Optional<VendorModule> product = this.vendorRepo.findById(vendorModule.getProductId());
        if(product.isPresent()){
            VendorModule productUpdate = product.get();
            productUpdate.setMain_category(vendorModule.getMain_category());
            productUpdate.setSub_category(vendorModule.getSub_category());
            productUpdate.setProduct_name(vendorModule.getProduct_name());
            productUpdate.setPrice(vendorModule.getPrice());
            productUpdate.setQuantity(vendorModule.getQuantity());
            productUpdate.setWarrenty(vendorModule.getWarrenty());
            productUpdate.setDescription(vendorModule.getDescription());
            productUpdate.setSpecification(vendorModule.getSpecification());
            productUpdate.setAvailabilityStatus(vendorModule.getAvailabilityStatus());
            vendorRepo.save(productUpdate);
            return productUpdate;
        }
        else
            throw new IllegalStateException("Record not found with id : " + vendorModule.getProductId());
    }
    public void updateAvailabilityStatus(String productId, String availabilityStatus) {
        VendorModule product = vendorRepo.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        product.setAvailabilityStatus(availabilityStatus);
        vendorRepo.save(product);
    }
    @Override
    public List<VendorModule> getProducts() {
        return this.vendorRepo.findAll();
    }

    @Override
    public List<VendorModule> outOffStock() {
        List<VendorModule> productModels = vendorRepo.findAll();

        List<VendorModule> outOfStockProduct = productModels.stream()
                .filter(product -> (product.getQuantity())<=20)
                .collect(Collectors.toList());
        return outOfStockProduct;
    }

    @Override
    public VendorModule getDataById(String id) {
        Optional<VendorModule> product = this.vendorRepo.findById(id);
        if(product.isPresent())
            return product.get();
        else
            throw new IllegalStateException("Record not found with id : " + id);
    }


}
