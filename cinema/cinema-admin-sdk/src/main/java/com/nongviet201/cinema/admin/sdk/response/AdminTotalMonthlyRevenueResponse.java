package com.nongviet201.cinema.admin.sdk.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminTotalMonthlyRevenueResponse {
    private Integer year;
    private Integer month;
    private Long totalRevenue;
}
