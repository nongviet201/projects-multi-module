package com.nongviet201.cinema.web.sdk.converter;

import com.nongviet201.cinema.web.sdk.response.WebUserStatisticResponse;
import org.springframework.stereotype.Service;

@Service
public class WebUserStatisticToResponseConverter {

    public WebUserStatisticResponse converter(
        int points,
        double rankPercent,
        int totalSpending
    ) {
        return WebUserStatisticResponse.builder()
            .points(points)
            .rankPercent(rankPercent)
            .totalSpending(totalSpending)
            .build();
    }
}
