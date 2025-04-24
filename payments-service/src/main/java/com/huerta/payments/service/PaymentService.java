package com.huerta.payments.service;

import java.util.List;

import com.huerta.core.dto.Payment;

public interface PaymentService {
    List<Payment> findAll();

    Payment process(Payment payment);
}
