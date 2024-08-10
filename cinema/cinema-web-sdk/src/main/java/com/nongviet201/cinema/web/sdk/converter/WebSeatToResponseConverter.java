package com.nongviet201.cinema.web.sdk.converter;

import com.nongviet201.cinema.web.sdk.response.WebSeatResponse;
import org.springframework.stereotype.Service;

@Service
public class WebSeatToResponseConverter {

    public WebSeatResponse convert(
         Integer id,
         String seatRow,
         Integer seatColumn,
         boolean status,
         String type,
         long price
    ) {
        return WebSeatResponse.builder()
            .id(id)
            .seatRow(seatRow)
            .seatColumn(seatColumn)
            .status(status)
            .type(type)
            .price(price)
            .build();
    }
}
