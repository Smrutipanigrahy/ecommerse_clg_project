package com.chs.module;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "UserDetails")
public class UserRegistration {

    @Id
    private String userId;
    @NotEmpty(message = "Enter Your Name")
    private String userName;
    @Indexed(unique = true)
    @NotEmpty(message = "Enter Your Email_Id")
    private String emailId;
    private String password;
    @NotNull(message = "Enter Country name")
    private Long mobileNo;
    @NotEmpty(message = "Enter Country name")
    private String country;
    @NotEmpty(message = "Enter State name")
    private String state;
}
