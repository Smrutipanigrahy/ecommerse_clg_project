package com.chs.ProductModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection  = "productdetail")
public class Product extends VendorModule{
        private List<VendorModule> vendorModuleList = new ArrayList<>();
    }

