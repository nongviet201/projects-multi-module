package com.nongviet201.cinema.web.sdk.controller.service;

import com.nongviet201.cinema.core.service.UserStatisticService;
import com.nongviet201.cinema.web.sdk.controller.decorator.WebPromotionDecorator;
import com.nongviet201.cinema.web.sdk.response.WebPromotionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebPromotionControllerService {

    private final WebPromotionDecorator decorator;
    private final UserStatisticService userStatisticService;

    public WebPromotionResponse getCurrentUserPromotion(

    ) {
       return decorator.decorate(
           userStatisticService.getCurrentUserStatistic().getPoints()
       );
    }
}
