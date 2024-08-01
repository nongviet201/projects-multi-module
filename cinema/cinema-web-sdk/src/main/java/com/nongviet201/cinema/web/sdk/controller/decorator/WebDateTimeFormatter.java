package com.nongviet201.cinema.web.sdk.controller.decorator;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class WebDateTimeFormatter {

    public String formatDate(
        LocalDate date
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    public String formatDateTime(
        LocalDateTime dateTime
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");
        return dateTime.format(formatter);
    }
}
