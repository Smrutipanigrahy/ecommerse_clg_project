package com.chs.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
//@Document(collection = "reviews")
public class Review {

    private String id;

    @NotBlank(message = "Please enter the name")
    private String name;

    @Min(value = 1, message = "Rating should not be less than 1")
    @Max(value = 5, message = "Rating should not be greater than 5")
    private int rating;

    @NotBlank(message = "Please enter the feedback")
    private String feedback;
}