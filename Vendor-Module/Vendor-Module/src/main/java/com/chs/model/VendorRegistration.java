package com.chs.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "VendorList")
public class VendorRegistration {
    @Id
    private String vendorId;
    @NotEmpty(message = "Enter Your Name")
    private String vendorName;

//    @Email
    @Indexed(unique = true)
    @NotEmpty(message = "Enter Your Email_Id")
    private String emailId;

    @NotEmpty(message = "Enter Product name")
    private String product;

    @NotNull
    private Long productLicenceNo;

}
