package com.nongviet201.cinema.admin.sdk.decorator;

import com.nongviet201.cinema.admin.sdk.converter.AdminTransactionToResponseConverter;
import com.nongviet201.cinema.admin.sdk.response.AdminTransactionResponse;
import com.nongviet201.cinema.core.entity.bill.Transaction;
import com.nongviet201.cinema.core.entity.cinema.Showtime;
import com.nongviet201.cinema.core.utils.WebFormatter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminTransactionDecorator {

    private final AdminTransactionToResponseConverter converter;
    private final WebFormatter webFormatter;

    public AdminTransactionResponse decorate(
        Transaction transaction
    ) {
        Showtime showtime = transaction.getBill().getShowtime();

        String screeningDateTime =
            webFormatter.formatTimeToHHmm(showtime.getStartTime())
            +" - "+
            webFormatter.formatTimeToHHmm(showtime.getEndTime())
            +" : "+
            webFormatter.formatDateToDDmmYYYY(showtime.getScreeningDate());

        String status = "code: " + transaction.getResponseCodeVNPAY().getCode() +" | "+ transaction.getResponseCodeVNPAY().getMessage();

        return converter.convert(
            transaction.getId(),
            transaction.getTransactionNo(),
            transaction.getBankCode(),
            transaction.getPaymentMethod(),
            transaction.getCinema().getId(),
            transaction.getCinema().getName(),
            webFormatter.formatFullDateTime(transaction.getPayDate()),
            status,
            transaction.getBill().getUser().getFullName(),
            transaction.getBill().getUser().getPhoneNumber(),
            showtime.getMovieSchedule().getMovie().getName(),
            screeningDateTime,
            transaction.getBill().getTotalPrice(),
            transaction.getBill().getDiscount(),
            transaction.getBill().getPoints()
        );
    }
}

