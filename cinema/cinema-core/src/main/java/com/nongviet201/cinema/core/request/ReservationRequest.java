package com.nongviet201.cinema.core.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationRequest {
    private Integer seatId;
    private Integer showtimeId;
}
