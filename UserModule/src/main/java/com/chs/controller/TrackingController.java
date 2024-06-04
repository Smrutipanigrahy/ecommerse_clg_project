package com.chs.controller;

import com.chs.module.Tracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/usertracking")
public class TrackingController {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/TrackingById/{Tracking_id}")
    public Tracking getTrackingDetails(@PathVariable String Tracking_id) {
        String trackingModuleUrl = "http://localhost:9090/";
        ResponseEntity<Tracking> response = restTemplate.getForEntity(
                trackingModuleUrl + "/tracking/getDataById/" + Tracking_id,
                Tracking.class
        );
        return response.getBody();
    }

}
