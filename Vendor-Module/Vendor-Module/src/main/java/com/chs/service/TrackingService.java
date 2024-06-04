package com.chs.service;


import com.chs.model.Order;
import com.chs.model.Tracking;

import java.util.List;


public interface TrackingService {

    Tracking creattracking(Tracking tracking);
    List<Tracking> getAllTracking();
    Tracking gettracingById(String id);
    Tracking updatedata(Tracking tracking);
    void storetrackingFororder(String tracking_id,Order order);


}
