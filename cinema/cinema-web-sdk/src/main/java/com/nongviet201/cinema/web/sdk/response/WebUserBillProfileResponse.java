package com.nongviet201.cinema.web.sdk.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WebUserBillProfileResponse {
    private Integer billId;
    private Integer showtimeId;
    private String payDate;
    private String movieName;
    private String auditoriumName;
    private String startTime;
    private String screeningDate;
    private String graphicsTypeAndAuditoriumType;
}
