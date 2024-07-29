package com.nongviet201.payment.vnpay.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentResponse {
    private String status;
    private String message;
    private String url;
}
