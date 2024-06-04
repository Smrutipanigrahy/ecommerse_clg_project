package com.chs.controller;

import com.chs.model.VendorModule;
import com.chs.service.VendorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @PostMapping("/addproduct")
    public ResponseEntity<?> addData(@Valid @RequestBody VendorModule vendorModule){
        return ResponseEntity.ok().body(this.vendorService.addProduct(vendorModule));
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateData(@RequestBody VendorModule vendorModule,@PathVariable String id){
        vendorModule.setProductId(id);
        return ResponseEntity.ok().body(this.vendorService.updateProduct(vendorModule));
    }


    //setting the availability status of the products
    @PutMapping("/{id}/{availability}")
    public ResponseEntity<String> updateAvailabilityStatus(
            @PathVariable("availability") String id,
            @RequestParam String availabilityStatus
    ) {
        try {
            vendorService.updateAvailabilityStatus(id, availabilityStatus);
            return ResponseEntity.ok("Availability status updated successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getData")
    public ResponseEntity<List<VendorModule>> getAllData(){
        return ResponseEntity.ok().body(this.vendorService.getProducts());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        return ResponseEntity.ok().body(this.vendorService.getDataById(id));
    }
    @GetMapping("/outOfStock")
    public ResponseEntity<Map<String, Object>> getOutOfStockProducts() {
        List<VendorModule> outOfStockProducts = vendorService.outOffStock();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "List of Out-of-Stock Products");
        response.put("products", outOfStockProducts);

        return ResponseEntity.ok(response);
//        return ResponseEntity.ok(outOfStockProducts);
//        return ResponseEntity.ok("Some products are out of stock.");
    }

}

