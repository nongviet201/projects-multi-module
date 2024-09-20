package com.nongviet201.cinema.admin.sdk.decorator;

import com.nongviet201.cinema.admin.sdk.converter.AdminBillToResponseConverter;
import com.nongviet201.cinema.admin.sdk.response.AdminBillResponse;
import com.nongviet201.cinema.core.entity.bill.BillCombo;
import com.nongviet201.cinema.core.entity.bill.BillSeat;
import com.nongviet201.cinema.core.entity.bill.Transaction;
import com.nongviet201.cinema.core.model.enums.cinema.SeatType;
import com.nongviet201.cinema.core.utils.WebFormatter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminBillDecorator {

    private final AdminBillToResponseConverter converter;
    private final WebFormatter webFormatter;

    public AdminBillResponse decorate(
        Transaction transaction,
        List<BillSeat> billSeat,
        List<BillCombo> billCombo
    ) {
        String showtimeDetail = transaction.getBill().getShowtime().getMovieSchedule().getMovie().getName()
            + "&nbsp;&nbsp;&nbsp; <span class='fs-14px fw-600'>" + webFormatter.formatTimeToHHmm(transaction.getBill().getShowtime().getStartTime())
            + "</span> - <span class='fs-14px'>" + webFormatter.formatFullDate(transaction.getBill().getShowtime().getScreeningDate())
            + "</span><br/> " + transaction.getBill().getShowtime().getAuditorium().getCinema().getName();

        return converter.billConvert(
            transaction.getBill().getUser().getFullName(),
            transaction.getBill().getUser().getEmail(),
            transaction.getBill().getId(),
            webFormatter.formatDateTimeToHHmmDDmmYYYY(transaction.getPayDate()),
            webFormatter.formatMoney(transaction.getBill().getTotalPrice() + transaction.getBill().getDiscount()),
            webFormatter.formatMoney(transaction.getBill().getDiscount()),
            webFormatter.formatMoney(transaction.getBill().getTotalPrice()),
            transaction.getResponseCodeVNPAY().getCode(),
            transaction.getBill().getStatus().getMessage(),
            showtimeDetail,
            billComboDecorate(billCombo),
            billSeatDecorate(billSeat)
        );
    }

    private List<AdminBillResponse.BillComboResponse> billComboDecorate(
        List<BillCombo> billCombos
    ) {
        return billCombos.stream()
            .map(e ->
                converter.billComboConvert(
                    e.getCombo().getName(),
                    e.getQuantity(),
                    webFormatter.formatMoney(e.getCombo().getPrice()),
                    webFormatter.formatMoney(e.getPrice())
                )
            )
            .toList();
    }

    private List<AdminBillResponse.BillSeatResponse> billSeatDecorate(
        List<BillSeat> billSeats
    ) {
        Map<SeatType, List<BillSeat>> groupedSeats = billSeats.stream()
            .collect(Collectors.groupingBy(seat -> seat.getSeat().getType()));

        List<AdminBillResponse.BillSeatResponse> listResponse = new ArrayList<>();

        for (SeatType seatType : SeatType.values()) {
            List<BillSeat> seatsOfType = groupedSeats.getOrDefault(seatType, new ArrayList<>());

            if (!seatsOfType.isEmpty()) {
                String listSeatName = seatsOfType.stream()
                    .map(seat -> seat.getSeat().getSeatRow() + seat.getSeat().getSeatColumn())
                    .collect(Collectors.joining(", "));

                listResponse.add(converter.billSeatConvert(
                    seatType,
                    listSeatName,
                    seatsOfType.size(),
                    webFormatter.formatMoney(seatsOfType.getFirst().getPrice()),
                    webFormatter.formatMoney(seatsOfType.stream().mapToLong(BillSeat::getPrice).sum())
                ));
            }
        }

        return listResponse;
    }

}
