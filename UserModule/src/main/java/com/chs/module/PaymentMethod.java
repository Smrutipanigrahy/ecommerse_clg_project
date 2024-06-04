package com.chs.module;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "paymentmethodDetail")
public class PaymentMethod extends CartItems {
    private String  payment_id;
    private String peep;
    private  String laja;
    private   String card_number;
    private String expiration_month;
    private String expiration_year;
    private String cvv;
    private  double amount;


}
