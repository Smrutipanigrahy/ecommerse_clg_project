package com.chs.controller;


import com.chs.model.Order;
import com.chs.model.Tracking;
import com.chs.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/tracking")
public class TrackingController {
    @Autowired
    private TrackingService trackingService;
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/gettrackingAndStore/{tracking_id}/{orderId}")
    public String gettrackingAndStore(@PathVariable String tracking_id, @PathVariable String orderId) {
        ResponseEntity<Order> responseEntity = restTemplate.getForEntity("http://ORDER-MODULE/orders/trackOrderHistory/" + orderId, Order.class);
        Order order = responseEntity.getBody();

        trackingService.storetrackingFororder(tracking_id,order );

        return "tracking fetched by ID and stored for the user";
    }
    @PostMapping("/adddetail")
    public ResponseEntity<?> addDatail( @RequestBody Tracking tracking){
        return ResponseEntity.ok().body(this.trackingService.creattracking(tracking));
    }
    @GetMapping("/getDataById/{id}")
    public ResponseEntity<?> getDataById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.trackingService.gettracingById(id));
    }
    @GetMapping("/getAllData")
    public ResponseEntity<List<Tracking>> getAllData(){
        return ResponseEntity.ok().body(this.trackingService.getAllTracking());
    }
    @PutMapping("/updataData/{id}")
    public ResponseEntity<?> updateData(@RequestBody Tracking tracking,@PathVariable String id){
        tracking.setTracking_id(id);
        return ResponseEntity.ok().body(this.trackingService.updatedata(tracking));
    }



}
