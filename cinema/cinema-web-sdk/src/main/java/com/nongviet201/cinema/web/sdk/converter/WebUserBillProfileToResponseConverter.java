package com.nongviet201.cinema.web.sdk.converter;

import com.nongviet201.cinema.web.sdk.response.WebUserBillProfileResponse;
import org.springframework.stereotype.Service;

@Service
public class WebUserBillProfileToResponseConverter {

    public WebUserBillProfileResponse convert(
        int billId,
        int showtimeId,
        String payDate,
        String movieName,
        String auditoriumName,
        String startTime,
        String screeningDate,
        String graphicsTypeAndAuditoriumType
    ) {
        return WebUserBillProfileResponse.builder()
            .billId(billId)
            .showtimeId(showtimeId)
            .payDate(payDate)
            .movieName(movieName)
            .auditoriumName(auditoriumName)
            .startTime(startTime)
            .screeningDate(screeningDate)
            .graphicsTypeAndAuditoriumType(graphicsTypeAndAuditoriumType)
            .build();
    }
}
