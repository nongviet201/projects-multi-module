package com.nongviet201.cinema.web.sdk.controller.decorator;

import com.nongviet201.cinema.core.entity.bill.Bill;
import com.nongviet201.cinema.core.entity.bill.BillCombo;
import com.nongviet201.cinema.core.entity.bill.BillSeat;
import com.nongviet201.cinema.core.entity.bill.Translation;
import com.nongviet201.cinema.core.entity.cinema.Showtime;
import com.nongviet201.cinema.core.entity.movie.Movie;
import com.nongviet201.cinema.core.service.BillComboService;
import com.nongviet201.cinema.core.service.BillSeatService;
import com.nongviet201.cinema.web.sdk.converter.WebBillDetailToResponseConverter;
import com.nongviet201.cinema.web.sdk.converter.WebUserBillProfileToResponseConverter;
import com.nongviet201.cinema.web.sdk.response.WebBillDetailResponse;
import com.nongviet201.cinema.web.sdk.response.WebUserBillProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WebUserBillProfileDecorator {
    private final WebUserBillProfileToResponseConverter converter;
    private final WebBillDetailToResponseConverter billDetailConverter;
    private final WebFormatter webFormatter;
    private final BillSeatService billSeatService;
    private final BillComboService billComboService;

    public WebUserBillProfileResponse decorate(
        Bill bill
    ) {
        String graphicsTypeAndAuditoriumType =
            bill.getShowtime().getAuditoriumType().toString() + bill.getShowtime().getGraphicsType().toString();

        return converter.convert(
            bill.getId(),
            bill.getShowtime().getId(),
            bill.getShowtime().getMovie().getName(),
            bill.getShowtime().getMovie().getPoster(),
            bill.getShowtime().getAuditorium().getName(),
            bill.getShowtime().getAuditorium().getCinema().getName(),
            webFormatter.formatTimeToHHmm(bill.getShowtime().getStartTime()),
            webFormatter.formatFullDate(bill.getShowtime().getScreeningDate()),
            graphicsTypeAndAuditoriumType
            );
    }

    public WebBillDetailResponse billDetailDecorator(
        Translation translation
    ) {
        Bill bill = translation.getBill();

        Showtime showtime = bill.getShowtime();
        Movie movie = showtime.getMovie();

        String seats = billSeatService.getBillSeatByBillId(bill.getId()).stream()
            .map(seat -> seat.getSeat().getSeatRow() + seat.getSeat().getSeatColumn())
            .collect(Collectors.joining(", "));

        String combos = billComboService.getBIllComboByBillId(bill.getId()).stream()
            .filter(combo -> combo.getQuantity() > 0)
            .map(combo -> combo.getCombo().getName() + " x " + combo.getQuantity())
            .collect(Collectors.joining(", "));

        // Format and return response
        return billDetailConverter.convert(
            movie.getName(),
            movie.getAgeRequirement().toString(),
            showtime.getGraphicsType() + " " + showtime.getAuditoriumType(),
            movie.getDuration(),
            showtime.getAuditorium().getCinema().getName(),
            webFormatter.formatDateToDDmmYYYY(showtime.getScreeningDate()),
            webFormatter.formatTimeToHHmm(showtime.getStartTime()),
            showtime.getAuditorium().getName(),
            seats,
            combos,
            translation.getPaymentMethod().toString(),
            webFormatter.formatMoney((int) bill.getTotalPrice()),
            bill.getPoints(),
            bill.getBarcode(),
            translation.getTransactionNo()
        );
    }


}
