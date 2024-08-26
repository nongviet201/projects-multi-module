package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.core.entity.cinema.Auditorium;
import com.nongviet201.cinema.core.entity.cinema.Seat;
import com.nongviet201.cinema.core.model.enums.cinema.SeatType;
import com.nongviet201.cinema.core.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminSeatService {

    private final SeatService seatService;

    public void seatCreate(
        Auditorium auditorium
    ) {

        for (int i = 0; i < auditorium.getTotalRowChair(); i++) {
            for (int j = 0; j < auditorium.getTotalColumnChair(); j++) {
                seatService.save(
                    Seat.builder()
                        .seatRow(numberToLetter(i))
                        .seatColumn(j)
                        .status(true)
                        .type(SeatType.NORMAL)
                        .auditorium(auditorium)
                        .build()
                );
            }
        }
    }

    private String numberToLetter(int number) {
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        // Chuyển đổi số sang chữ cái tương ứng
        if (number >= 1 && number <= 26) {
            return letters[number - 1];
        } else {
            return null;
        }
    }
}
