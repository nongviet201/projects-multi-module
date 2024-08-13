package com.nongviet201.cinema.core.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VnPayReturnRequest {
    private Integer billId;
    private String responseCode;
    private String transactionNo;
    private String transactionStatus;
    private String bankCode;
    private String payDate;
    private Long amount;
}
