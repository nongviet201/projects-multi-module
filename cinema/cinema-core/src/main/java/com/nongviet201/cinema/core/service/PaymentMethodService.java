package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.model.enums.PaymentMethod;
import com.nongviet201.cinema.core.request.ToPaymentRequest;

public interface PaymentMethodService {
    String transitionToPaymentMethod(PaymentMethod paymentMethod, ToPaymentRequest request);
}
