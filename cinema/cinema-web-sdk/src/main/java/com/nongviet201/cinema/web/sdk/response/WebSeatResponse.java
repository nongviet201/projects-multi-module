package com.nongviet201.cinema.web.sdk.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WebSeatResponse {
    private Integer id;
    private String seatRow;
    private Integer seatColumn;
    private boolean status;
    private String type;
    private long price;
}
