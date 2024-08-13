package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.model.enums.PaymentMethod;
import com.nongviet201.cinema.core.request.ToPaymentRequest;
import com.nongviet201.cinema.core.service.PaymentMethodService;
import com.nongviet201.cinema.payment.vnpay.service.VnPayService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final VnPayService vnPayService;

    @Override
    public String transitionToPaymentMethod(
            PaymentMethod paymentMethod,
            ToPaymentRequest request
    ) {
        if (paymentMethod == PaymentMethod.VNPAY) {
            vnPayService.createPayment(
                    request.getBillId(),
                    request.getAmount(),
                    request.getTimeRemain()
            );
        }
        return "";
    }
}
