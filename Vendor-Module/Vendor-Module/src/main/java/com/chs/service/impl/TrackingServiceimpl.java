package com.chs.service.impl;
import com.chs.model.Order;
import com.chs.model.Tracking;
import com.chs.repo.TrackingRepo;
import com.chs.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TrackingServiceimpl implements TrackingService {
    @Autowired
    private TrackingRepo trackingrepos;

    @Override
    public Tracking creattracking(Tracking tracking) {
        return this.trackingrepos.save(tracking);
    }

    @Override
    public List<Tracking> getAllTracking() {
        return trackingrepos.findAll();
    }

    @Override
    public Tracking gettracingById(String id) {
        Optional<Tracking> tracking= this.trackingrepos.findById(id);
        if(tracking.isPresent())
            return tracking.get();
        else
            throw new IllegalStateException("Record not found with id : " + id);
    }

    @Override
    public Tracking updatedata(Tracking tracking) {
        Optional<Tracking> tracking1 = this.trackingrepos.findById(tracking.getTracking_id());
        if(tracking1.isPresent()){
            Tracking trackingUpdate = tracking1.get();
            trackingUpdate.setEstimated_Delivery_Time(tracking.getEstimated_Delivery_Time());
            trackingUpdate.setShipping_By(tracking.getShipping_By());
            trackingUpdate.setStatus(tracking.getStatus());


            trackingrepos.save(trackingUpdate);
            return trackingUpdate;
        }
        else
            throw new IllegalStateException("Record not found with id : " + tracking.getTracking_id());
    }

    @Override
    public void storetrackingFororder(String tracking_id, Order order) {
        // Assuming you have a User entity with a field to store products
        Tracking tracking = trackingrepos.findById(tracking_id).orElseThrow(() -> new IllegalStateException("tracking not found with ID: " + tracking_id));


        if (tracking != null) {
            Order convertedtracking = convertToDataEntity(order);
            tracking.getTrackingList().add(convertedtracking);
            trackingrepos.save(tracking);
        } else {
            throw new IllegalStateException("tracking_id not found with ID: " +  tracking_id);
        }
    }
    private Order convertToDataEntity(Order productData) {
        Order dataEntity = new Order();

        dataEntity.setOrderId(productData.getOrderId());

        return dataEntity;
    }



}



