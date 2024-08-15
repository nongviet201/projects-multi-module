package com.nongviet201.cinema.web.sdk.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WebBillDetailResponse {
    private String movieName;
    private Integer ageRequirement;
    private String graphicAndTranslateType;
    private Integer duration;
    private String cinemaName;
    private String screeningDate;
    private String startTime;
    private String auditoriumName;
    private String seatName;
    private String combo;
    private String paymentMethod;
    private String amount;
    private Integer point;
    private String transactionNo;

}
