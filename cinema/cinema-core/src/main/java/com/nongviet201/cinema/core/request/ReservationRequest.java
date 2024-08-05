package com.nongviet201.cinema.core.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequest {
    private Integer userId;
    private Integer seatId;
    private Integer showtimeId;
}
