package com.chs.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "VendorReview")
public class VendorReview extends Review {
   private List<Review> reviewList = new ArrayList<>();

    }


