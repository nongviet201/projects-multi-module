package com.nongviet201.cinema.core.utils;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class DateTimeUtils {

    public static DateTimeRange getTimeRange(String time) {
        LocalDateTime endDate;
        LocalDateTime startDate;

        switch (time) {
            case "week":
                endDate = LocalDateTime.now();
                startDate = endDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                break;

            case "lastWeek":
                endDate = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
                startDate = endDate.minusWeeks(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                break;

            case "month":
                endDate = LocalDateTime.now();
                startDate = endDate.with(TemporalAdjusters.firstDayOfMonth());
                break;

            case "lastMonth":
                endDate = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()).minusDays(1);
                startDate = endDate.with(TemporalAdjusters.firstDayOfMonth());
                break;

            default:
                throw new IllegalArgumentException("Invalid time parameter: " + time);
        }

        return new DateTimeRange(startDate, endDate);
    }

    @Getter
    public static class DateTimeRange {
        private final LocalDateTime startDate;
        private final LocalDateTime endDate;

        public DateTimeRange(LocalDateTime startDate, LocalDateTime endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

    }
}
