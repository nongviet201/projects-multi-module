package com.nongviet201.cinema.web.sdk.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequest {
    private Integer userId;
    private Integer seatId;
    private Integer showtimeId;
}
