package com.nongviet201.cinema.web.sdk.controller.decorator;

import com.nongviet201.cinema.core.entity.bill.Bill;
import com.nongviet201.cinema.core.model.enums.BillStatus;
import com.nongviet201.cinema.web.sdk.converter.WebBillToResponseConverter;
import com.nongviet201.cinema.web.sdk.converter.WebUserBillProfileToResponseConverter;
import com.nongviet201.cinema.web.sdk.response.WebBillResponse;
import com.nongviet201.cinema.web.sdk.response.WebUserBillProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class WebUserBillProfileDecorator {
    private final WebUserBillProfileToResponseConverter converter;
    private final WebDateTimeFormatter dateTimeFormatter;

    public WebUserBillProfileResponse decorate(
        Bill bill
    ) {
        String graphicsTypeAndAuditoriumType =
            bill.getShowtime().getGraphicsType().toString() + bill.getShowtime().getAuditoriumType().toString();

        return converter.convert(
            bill.getId(),
            bill.getShowtime().getId(),
            bill.getShowtime().getMovie().getName(),
            bill.getShowtime().getMovie().getPoster(),
            bill.getShowtime().getAuditorium().getName(),
            bill.getShowtime().getAuditorium().getCinema().getName(),
            dateTimeFormatter.formatTimeToHHmm(bill.getShowtime().getStartTime()),
            dateTimeFormatter.formatFullDate(bill.getShowtime().getScreeningDate()),
            graphicsTypeAndAuditoriumType
            );
    }
}
