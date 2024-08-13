package com.nongviet201.cinema.web.sdk.converter;

import com.nongviet201.cinema.web.sdk.response.WebBillResponse;
import org.springframework.stereotype.Service;

@Service
public class WebBillToResponseConverter {

    public WebBillResponse convert(
        int id,
        long amount,
        String updatedAt,
        boolean status,
        String statusMessage
    ) {
        return WebBillResponse.builder()
            .id(id)
            .amount(amount)
            .updatedAt(updatedAt)
            .status(status)
            .statusMessage(statusMessage)
            .build();
    }
}
