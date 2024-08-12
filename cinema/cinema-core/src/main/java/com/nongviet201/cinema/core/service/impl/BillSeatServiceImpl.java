package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.bill.BaseTicketPrice;
import com.nongviet201.cinema.core.entity.bill.Bill;
import com.nongviet201.cinema.core.entity.bill.BillSeat;
import com.nongviet201.cinema.core.entity.cinema.Seat;
import com.nongviet201.cinema.core.entity.cinema.Showtime;
import com.nongviet201.cinema.core.exception.BadRequestException;
import com.nongviet201.cinema.core.model.enums.DayType;
import com.nongviet201.cinema.core.repository.BillRepository;
import com.nongviet201.cinema.core.repository.BillSeatRepository;
import com.nongviet201.cinema.core.repository.SeatRepository;
import com.nongviet201.cinema.core.service.BaseTicketPriceService;
import com.nongviet201.cinema.core.service.BillSeatService;
import com.nongviet201.cinema.core.service.ShowtimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;

@Service
@AllArgsConstructor
public class BillSeatServiceImpl implements BillSeatService {
    private final BillRepository billRepository;
    private final SeatRepository seatRepository;
    private final BillSeatRepository billSeatRepository;
    private final BaseTicketPriceService baseTicketPriceService;
    private final ShowtimeService showtimeService;

    @Override
    public long createBillSeat(Integer billId, Integer SeatId) {
        Bill bill = billRepository.findById(billId)
            .orElseThrow(() -> new BadRequestException("Bill không tồn tại"));

        Seat seat = seatRepository.findById(SeatId)
            .orElseThrow(() -> new BadRequestException("Ghế không tồn tại"));

        Showtime showtime = showtimeService.getShowtimeById(bill.getShowTime().getId());

        DayType dayType;
        if (showtime.getScreeningDate().getDayOfWeek().equals(DayOfWeek.SATURDAY) ||
            showtime.getScreeningDate().getDayOfWeek().equals(DayOfWeek.SUNDAY)
        ) {
            dayType = DayType.WEEKEND;
        } else {
            dayType = DayType.WEEKDAY;
        }

        long price = baseTicketPriceService.getPrice(
            seat.getType(),
            showtime.getGraphicsType(),
            showtime.getScreeningTimeType(),
            dayType,
            showtime.getAuditorium().getAuditoriumType(),
            showtime.getAuditorium().getCinema()
        );

        billSeatRepository.save(
            BillSeat.builder()
                .bill(bill)
                .seat(seat)
                .price(price)
                .build()
        );

        return price;
    }

    @Override
    public BillSeat getBillSeatByBillId(Integer billId) {
        return null;
    }
}
