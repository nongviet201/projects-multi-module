package com.nongviet201.cinema.core.response;

import com.nongviet201.cinema.core.model.enums.ReservationType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationResponse {
    Integer seatId;
    Integer showtimeId;
    ReservationType status;
}
