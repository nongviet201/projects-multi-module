package com.nongviet201.cinema.web.sdk.controller.decorator;

import com.nongviet201.cinema.core.model.entity.bill.Bill;
import com.nongviet201.cinema.web.sdk.converter.WebBillToResponseConverter;
import com.nongviet201.cinema.web.sdk.response.WebBillResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebBillDecorator {
    private final WebBillToResponseConverter converter;
    private final WebDateTimeFormatter dateTimeFormatter;

    public WebBillResponse decorate(
        Bill bill
    ) {
        return converter.convert(
            bill.getId(),
            bill.getTotalPrice(),
            dateTimeFormatter.formatFullDateTime(bill.getUpdateAt())
        );
    }
}
