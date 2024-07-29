package com.nongviet201.cinema.web.sdk.Request;

import com.nongviet201.cinema.core.model.entity.bill.Reservation;
import com.nongviet201.cinema.core.model.enums.ReservationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequest {
    private Integer userId;
    private Integer seatId;
    private Integer showtimeId;
    private ReservationType status;
}
