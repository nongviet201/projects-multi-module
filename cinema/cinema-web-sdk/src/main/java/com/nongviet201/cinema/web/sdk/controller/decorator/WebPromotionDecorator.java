package com.nongviet201.cinema.web.sdk.controller.decorator;

import com.nongviet201.cinema.core.entity.user.UserStatistic;
import com.nongviet201.cinema.web.sdk.converter.WebPromotionToResponseConverter;
import com.nongviet201.cinema.web.sdk.response.WebPromotionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebPromotionDecorator {

    private final WebPromotionToResponseConverter converter;

    public WebPromotionResponse decorate(
        Integer points
    ) {
        return converter.convert(
            points,
            null
        );
    }
}
