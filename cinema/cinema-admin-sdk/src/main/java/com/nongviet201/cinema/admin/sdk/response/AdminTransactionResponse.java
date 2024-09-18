package com.nongviet201.cinema.admin.sdk.response;

import com.nongviet201.cinema.core.model.enums.bill.PaymentMethod;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminTransactionResponse {
    private int id;
    private String transactionNo;
    private String bankCode;
    private PaymentMethod paymentMethod;
    private Integer cinemaId;
    private String cinemaName;
    private String payDate;
    private String status;
    private String userName;
    private String phoneNumber;
    private String movieName;
    private String screeningDateTime;
    private Long totalPrice;
    private Long discountAmount;
    private Integer points;
}
