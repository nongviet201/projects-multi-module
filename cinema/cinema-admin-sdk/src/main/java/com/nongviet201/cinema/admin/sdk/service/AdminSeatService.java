package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.request.UpsertSeatRequest;
import com.nongviet201.cinema.core.entity.cinema.Auditorium;
import com.nongviet201.cinema.core.entity.cinema.Seat;
import com.nongviet201.cinema.core.model.enums.cinema.SeatType;
import com.nongviet201.cinema.core.service.SeatService;
import com.nongviet201.cinema.core.utils.EnumService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminSeatService {

    private final SeatService seatService;
    private final EnumService enumService;

    public List<Seat> getAllSeatByAuditoriumId(
        int audId
    ) {
        return seatService.getAllSeatsByAuditoriumIdOrderBySeatRowAsc(audId);
    }


    public List<Seat> getAllSeatDeletedByAuditoriumId(
        int audId
    ) {
        return seatService.getAllSeatDeletedByAuditoriumIdOrderBySeatRowAsc(audId);
    }

    public SeatType[] getAllSeatTypes() {
        return SeatType.values();
    }

    public void seatUpdate(
        UpsertSeatRequest.seatUpdate request
    ) {
        for (Integer id : request.getSeatIds()) {
            Seat seat = seatService.getSeatById(id);
            seat.setStatus(request.isStatus());
            seat.setType(enumService.getEnumValueByName(SeatType::valueOf, request.getType(), "SeatType"));
            seat.setBlock(request.isBlock());
            seatService.save(seat);
        }
    }

    public void updateSeatDelete(
        Integer[] seatIds,
        boolean deleted
    ) {
        for (Integer id : seatIds) {
            Seat seat = seatService.getSeatById(id);
            seat.setDeleted(deleted);
            seatService.save(seat);
        }
    }

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
