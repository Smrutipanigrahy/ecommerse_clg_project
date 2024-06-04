package com.chs.module;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "UserReviews")
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