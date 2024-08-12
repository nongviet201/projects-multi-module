package com.nongviet201.cinema.web.sdk.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WebReservationResponse {
    private Integer seatId;
    private Integer showtimeId;
    private String status;
    private Boolean isCurrentUser;
    private Long timeRemaining;
}
