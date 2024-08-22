package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.response.AdminTotalMonthlyRevenueResponse;
import com.nongviet201.cinema.core.entity.bill.Translation;
import com.nongviet201.cinema.core.payment.vnpay.code.ResponseCodeVNPAY;
import com.nongviet201.cinema.core.service.TranslationService;
import com.nongviet201.cinema.core.utils.DateTimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminRevenueService {

    private final TranslationService translationService;

    public List<AdminTotalMonthlyRevenueResponse> getTotalMonthlyRevenueOfAllCinemasForYear(
        int year
    ) {
        List<AdminTotalMonthlyRevenueResponse> totalMonthlyRevenueOfAllCinemas = new ArrayList<>();

        int currentMonth = LocalDateTime.now().getMonthValue();

        for (int month = 1; month <= 12; month++) {

            if (year == LocalDateTime.now().getYear() && month > currentMonth) {
                continue;
            }

            LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);
            LocalDateTime endDate = startDate.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59);

            List<Translation> translations = translationService.getAllTranslationByTimeAndStatusAndCode(
                startDate,
                endDate,
                true,
                ResponseCodeVNPAY.PAYMENT_SUCCESS
            );

            long totalRevenueOfMonth = translations.stream()
                .mapToLong(translation -> translation.getBill().getTotalPrice())
                .sum();
            // Thêm dữ liệu vào danh sách kết quả
            totalMonthlyRevenueOfAllCinemas.add(
                AdminTotalMonthlyRevenueResponse.builder()
                    .year(year)
                    .month(month)
                    .totalRevenue(totalRevenueOfMonth)
                    .build()
            );
        }

        return totalMonthlyRevenueOfAllCinemas;
    }

    public List<Translation> getTotalRevenueByTime(String time) {
        DateTimeUtils.DateTimeRange timeRange = DateTimeUtils.getTimeRange(time);

        return translationService.getAllTranslationByTimeAndStatusAndCode(
            timeRange.getStartDate(),
            timeRange.getEndDate(),
            true,
            ResponseCodeVNPAY.PAYMENT_SUCCESS
        );
    }

}
