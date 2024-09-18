package com.nongviet201.cinema.core.utils;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

public class DateTimeUtils {

    public static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static LocalTime parseTime(String timeString) {
        return LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
    }

    public static LocalDateTime parseDateTime(String timeString) {
        try {
            return LocalDateTime.parse(timeString, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            try {
                LocalDate date = LocalDate.parse(timeString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                return date.atStartOfDay();
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("Invalid date/time format. Expected dd/MM/yyyy or dd/MM/yyyy HH:mm", ex);
            }
        }
    }

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
