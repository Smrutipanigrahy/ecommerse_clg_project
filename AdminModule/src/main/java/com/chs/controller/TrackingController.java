package com.chs.controller;

import com.chs.module.Tracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@RestController
@RequestMapping("/admintracking")
public class TrackingController {
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/trackingList")
    public List<Tracking> gettrackingList() {
        // Make a REST API call to the Product Service to fetch product data
        String trackingServiceUrl = "http://localhost:9090";
        ResponseEntity<List<Tracking>> response = restTemplate.exchange(
                trackingServiceUrl + "/tracking/getAllData",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Tracking>>() {}
        );
        return response.getBody();
    }
}
