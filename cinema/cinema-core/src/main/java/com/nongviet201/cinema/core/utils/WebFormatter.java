package com.nongviet201.cinema.core.utils;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class WebFormatter {

    public String formatFullDate(
        LocalDate date
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy", new Locale("vi", "VN"));
        return date.format(formatter);
    }

    public String formatDateToEEEEddMM(
        LocalDate date
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd/MM", new Locale("vi", "VN"));
        return date.format(formatter);
    }

    public String formatDateToDDmmYYYY(
        LocalDate date
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("vi", "VN"));
        return date.format(formatter);
    }

    public String formatDateToEEEEmmYYYY(
            LocalDate date
        ) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MM/yyyy", new Locale("vi", "VN"));
            return date.format(formatter);
    }

    public String formatDateTimeToEEEEmmYYYY(
            LocalDateTime date
        ) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MM/yyyy", new Locale("vi", "VN"));
            return date.format(formatter);
    }

    public String formatDateTimeTommYYYY(
        LocalDateTime date
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        return date.format(formatter);
    }

    public String formatDateTimeToHHmmDDmmYYYY(
        LocalDateTime date
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy ");
        return date.format(formatter);
    }

    public String formatFullDateTime(
        LocalDateTime dateTime
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm - EEEE, dd/MM/yyyy ", new Locale("vi", "VN"));
        return dateTime.format(formatter);
    }

    public String formatTimeToHHmm(
            LocalTime time
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", new Locale("vi", "VN"));
        return time.format(formatter);
    }

    public long calculateRemainingMillis(LocalDateTime startOrderTime) {
        LocalDateTime endTime = startOrderTime.plusMinutes(10);

        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(now, endTime);

        long remainingMillis = duration.toMillis();

        remainingMillis -= 10000;

        return Math.max(remainingMillis, 0);
    }

    public String formatMoney(
        long totalSpending
    ) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(totalSpending);
    }
}
