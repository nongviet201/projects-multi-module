package com.nongviet201.cinema.web.sdk.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WebPromotionResponse {
    private Integer points;
    private String coupons;
}
