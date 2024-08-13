package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.request.ToPaymentRequest;
import com.nongviet201.cinema.core.request.VnPayReturnRequest;

public interface PaymentService {
    String transitionToPaymentMethod(ToPaymentRequest request);
    Integer PaymentVnPayReturnCheck(VnPayReturnRequest request);
}
