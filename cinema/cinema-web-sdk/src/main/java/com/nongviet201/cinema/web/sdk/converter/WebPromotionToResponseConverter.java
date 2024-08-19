package com.nongviet201.cinema.web.sdk.converter;

import com.nongviet201.cinema.web.sdk.response.WebPromotionResponse;
import org.springframework.stereotype.Service;

@Service
public class WebPromotionToResponseConverter {

    public WebPromotionResponse convert(
        Integer points,
        String coupons
    ){
        return WebPromotionResponse.builder()
            .points(points)
            .coupons(coupons)
            .build();
    }
}
