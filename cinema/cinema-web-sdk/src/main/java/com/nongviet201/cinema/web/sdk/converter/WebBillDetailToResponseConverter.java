package com.nongviet201.cinema.web.sdk.converter;

import com.nongviet201.cinema.web.sdk.response.WebBillDetailResponse;
import org.springframework.stereotype.Service;

@Service
public class WebBillDetailToResponseConverter {

    public WebBillDetailResponse convert(
        String movieName,
        Integer ageRequirement,
        String graphicAndTranslateType,
        Integer duration,
        String cinemaName,
        String screeningDate,
        String startTime,
        String auditoriumName,
        String seatName,
        String combo,
        String paymentMethod,
        String amount,
        Integer point,
        String transactionNo
    ){
        return WebBillDetailResponse.builder()
            .movieName(movieName)
            .ageRequirement(ageRequirement)
            .graphicAndTranslateType(graphicAndTranslateType)
            .duration(duration)
            .cinemaName(cinemaName)
            .screeningDate(screeningDate)
            .startTime(startTime)
            .auditoriumName(auditoriumName)
            .seatName(seatName)
            .combo(combo)
            .paymentMethod(paymentMethod)
            .amount(amount)
            .point(point)
            .transactionNo(transactionNo)
            .build();
    }
}
