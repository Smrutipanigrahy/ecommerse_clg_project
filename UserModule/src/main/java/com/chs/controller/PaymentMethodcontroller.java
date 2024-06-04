package com.chs.controller;

import com.chs.module.PaymentMethod;
import com.chs.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/payment")
public class PaymentMethodcontroller {
    @Autowired
    private PaymentMethodService paymentMethodService;
    @PostMapping("/adddetail")
    public ResponseEntity<?> addDatail(@RequestBody PaymentMethod paymentMethod){
        return ResponseEntity.ok().body(this.paymentMethodService.addPayment(paymentMethod));
    }
    @GetMapping("/getDataById/{id}")
    public ResponseEntity<?> getDataById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.paymentMethodService.getpaymentById(id));
    }
    @GetMapping("/total-with-payment/{userid}")
    public  double gettotalpricepayment(@PathVariable String userid){
        return paymentMethodService.getTotalpaymentAmount(userid);
    }

}
