package com.nongviet201.cinema.web.sdk.controller.decorator;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class WebDateTimeFormatter {

    public String formatDate(
        LocalDate date
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy", new Locale("vi", "VN"));
        return date.format(formatter);
    }

    public String formatDateTime(
        LocalDateTime dateTime
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm - EEEE, dd/MM/yyyy ", new Locale("vi", "VN"));
        return dateTime.format(formatter);
    }
}
