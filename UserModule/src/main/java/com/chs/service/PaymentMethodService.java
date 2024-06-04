package com.chs.service;

import com.chs.module.PaymentMethod;

public interface PaymentMethodService {
    public  Double getTotalpaymentAmount(String userId);
    PaymentMethod addPayment(PaymentMethod payment);
    PaymentMethod getpaymentById(String payment_id);

}
