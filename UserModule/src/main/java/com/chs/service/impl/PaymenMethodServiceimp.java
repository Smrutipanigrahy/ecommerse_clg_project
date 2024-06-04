package com.chs.service.impl;

import com.chs.module.CartItems;
import com.chs.module.PaymentMethod;
import com.chs.repository.CartItemsRepo;
import com.chs.repository.PaymentMethodrepos;
import com.chs.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymenMethodServiceimp implements PaymentMethodService {
    @Autowired
    private PaymentMethodrepos paymentMethodrepos;
    @Autowired
    private CartItemsRepo cartItemsRepo;

    @Override
    public Double getTotalpaymentAmount(String userId) {
        List<CartItems>cartItems=cartItemsRepo.findByUserId(userId);
        List<PaymentMethod> paymentMethods=paymentMethodrepos.findByUserId(userId);
        double totalprice=0.0;
        for(CartItems cartItems1:cartItems){
            totalprice+= cartItems1.getTotalPrice();
        }
        for(PaymentMethod paymentMethod:paymentMethods){
            totalprice +=paymentMethod.getAmount();
        }
        return  totalprice;
    }

    @Override
    public PaymentMethod addPayment(PaymentMethod payment) {
        return this.paymentMethodrepos.save(payment);
    }

    @Override
    public PaymentMethod getpaymentById(String payment_id) {
        Optional<PaymentMethod> paymentMethod= this.paymentMethodrepos.findById(payment_id);
        if(paymentMethod.isPresent())
            return paymentMethod.get();
        else
            throw new IllegalStateException("Record not found with id : " + payment_id);
    }


}




