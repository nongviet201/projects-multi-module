package com.nongviet201.cinema.web.sdk.converter;

import com.nongviet201.cinema.web.sdk.response.WebBillResponse;
import org.springframework.stereotype.Service;

@Service
public class WebBillToResponseConverter {

    public WebBillResponse convert(
        int id,
        long amount,
        String updateAt
    ) {
        return WebBillResponse.builder()
            .id(id)
            .amount(amount)
            .updatedAt(updateAt)
            .build();
    }
}
