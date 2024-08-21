package com.nongviet201.cinema.core.request;

import com.nongviet201.cinema.core.model.enums.bill.PaymentMethod;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ToPaymentRequest {
    private Integer translationId;
    private Long amount;
    private Integer timeRemain;
    private PaymentMethod paymentMethod;
}
