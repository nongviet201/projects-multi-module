package com.nongviet201.cinema.web.sdk.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillRequest {
    Integer userId;
    Integer showtimeId;
    long totalPrice;
}
