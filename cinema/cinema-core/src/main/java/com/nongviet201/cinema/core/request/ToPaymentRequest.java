package com.nongviet201.cinema.core.request;

import com.nongviet201.cinema.core.model.enums.PaymentMethod;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
public class ToPaymentRequest {
    private Integer billId;
    private Long amount;
    private Integer timeRemain;
    private PaymentMethod paymentMethod;
}
