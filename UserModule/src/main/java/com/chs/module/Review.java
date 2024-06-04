package com.chs.module;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "reviews")
public class Review {
    @NotBlank(message = "Please enter your Id")
    private String id;
    @NotBlank(message = "Please enter the name")
    private String name;
    @NotBlank(message = "Please enter the rating")
    private int rating;
    @NotBlank(message = "Please enter the feedback")
    private String feedback;
}
