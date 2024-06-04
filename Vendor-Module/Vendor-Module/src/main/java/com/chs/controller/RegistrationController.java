package com.chs.controller;

import com.chs.model.VendorRegistration;
import com.chs.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vendorRegistration")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;
    @PostMapping("/addDetails")
    public ResponseEntity<?> addVendorDetails(@Valid @RequestBody VendorRegistration registration){
        return ResponseEntity.ok().body(this.registrationService.addDetails(registration));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateData(@Valid @RequestBody VendorRegistration registration,@PathVariable String id){
        registration.setVendorId(id);
        return ResponseEntity.ok().body(this.registrationService.updateDetails(registration));
    }

    @GetMapping("/vendorDetails")
    public ResponseEntity<List<VendorRegistration>> getAllVendorDetails(){
        return ResponseEntity.ok().body(this.registrationService.getAllDetails());
    }
}
