package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.response.AdminTotalMonthlyRevenueResponse;
import com.nongviet201.cinema.core.entity.bill.Transaction;
import com.nongviet201.cinema.core.payment.vnpay.code.ResponseCodeVNPAY;
import com.nongviet201.cinema.core.service.TransactionService;
import com.nongviet201.cinema.core.utils.DateTimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminRevenueService {

    private final TransactionService transactionService;

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

            List<Transaction> transactions = transactionService.getAllTranslationByTimeAndStatusAndCode(
                startDate,
                endDate,
                true,
                ResponseCodeVNPAY.PAYMENT_SUCCESS
            );

            long totalRevenueOfMonth = transactions.stream()
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

    public List<Transaction> getTotalRevenueByTime(String time) {
        DateTimeUtils.DateTimeRange timeRange = DateTimeUtils.getTimeRange(time);

        return transactionService.getAllTranslationByTimeAndStatusAndCode(
            timeRange.getStartDate(),
            timeRange.getEndDate(),
            true,
            ResponseCodeVNPAY.PAYMENT_SUCCESS
        );
    }

}
