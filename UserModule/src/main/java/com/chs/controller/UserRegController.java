package com.chs.controller;

import com.chs.module.UserRegistration;
import com.chs.service.UserRegService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("userRegistration")
public class UserRegController {
    @Autowired
    private UserRegService registrationService;
    @PostMapping("/addDetails")
    public ResponseEntity<?> addVendorDetails(@Valid @RequestBody UserRegistration registration){
        return ResponseEntity.ok().body(this.registrationService.addDetails(registration));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserData(@Valid @RequestBody UserRegistration registration,@PathVariable String id){
        registration.setUserId(id);
        return ResponseEntity.ok().body(this.registrationService.updateDetails(registration));
    }

    @GetMapping("/getAllData")
    public ResponseEntity<List<UserRegistration>> getAllUserDetails(){
        return ResponseEntity.ok().body(this.registrationService.getAllDetails());
    }

    @GetMapping("/getDataById/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.registrationService.getUserById(id));
    }
}
