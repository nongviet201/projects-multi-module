package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.request.UpsertSeatRequest;
import com.nongviet201.cinema.core.entity.cinema.Auditorium;
import com.nongviet201.cinema.core.entity.cinema.Seat;
import com.nongviet201.cinema.core.model.enums.cinema.SeatType;
import com.nongviet201.cinema.core.service.AuditoriumService;
import com.nongviet201.cinema.core.service.BlockService;
import com.nongviet201.cinema.core.service.SeatService;
import com.nongviet201.cinema.core.utils.EnumService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminSeatService {

    private final SeatService seatService;
    private final BlockService blockService;
    private final EnumService enumService;
    private final AuditoriumService auditoriumService;

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


    public void seatOrderVerification(
        Integer audId
    ) {
        List<Seat> seats = getAllSeatByAuditoriumId(audId);

        Map<String, List<Seat>> seatRowsMap = seats.stream()
            .collect(Collectors.groupingBy(Seat::getSeatRow));

        for (Map.Entry<String, List<Seat>> entry : seatRowsMap.entrySet()) {
            List<Seat> seatList = entry.getValue();

            int count = 1;

            for (Seat seat : seatList) {
                if (seat.getSeatColumn() != count)  {
                    seat.setSeatColumn(count);
                    seatService.save(seat);
                }
                count++;
            }
        }
    }

    public void createSeat(List<UpsertSeatRequest.SeatCreate> request) {
        // Lấy tất cả các ghế của auditorium
        List<Seat> seats = getAllSeatByAuditoriumId(request.get(0).getAudId());

        // Nhóm các ghế theo hàng (row)
        Map<String, List<Seat>> seatRowsMap = seats.stream()
            .collect(Collectors.groupingBy(Seat::getSeatRow));

        // Duyệt qua từng request để tạo thêm ghế
        for (UpsertSeatRequest.SeatCreate e : request) {
            List<Seat> currentRowSeats = seatRowsMap.getOrDefault(e.getSeatRow(), new ArrayList<>());

            // Kiểm tra nếu startColumn bằng 0
            if (e.getStartColumn() == 0) {
                e.setStartColumn(1); // Chuyển startColumn thành 1

                // Tăng giá trị seatColumn của tất cả ghế có seatColumn >= 1 lên 1
                for (Seat seat : currentRowSeats) {
                    if (seat.getSeatColumn() >= 1) {
                        seat.setSeatColumn(seat.getSeatColumn() + 1);
                        seatService.save(seat); // Lưu ghế đã thay đổi
                    }
                }

                // Tạo ghế mới tại vị trí startColumn = 1
                Seat newSeat = Seat.builder()
                    .auditorium(auditoriumService.getAuditoriumById(e.getAudId())) // Lấy auditorium từ service
                    .seatRow(e.getSeatRow())
                    .seatColumn(1) // Đặt seatColumn là 1
                    .type(SeatType.NORMAL) // Loại ghế mặc định
                    .status(true) // Đặt trạng thái mặc định
                    .build();

                seatService.save(newSeat); // Lưu ghế mới
            } else {
                // Xử lý bình thường nếu startColumn không phải là 0
                boolean found = false;

                // Sắp xếp các ghế theo thứ tự `seatColumn`
                currentRowSeats.sort(Comparator.comparingInt(Seat::getSeatColumn));

                for (int i = 0; i < currentRowSeats.size(); i++) {
                    Seat seat = currentRowSeats.get(i);

                    // Kiểm tra nếu có khoảng trống giữa các seatColumn
                    if (seat.getSeatColumn() < e.getStartColumn()) {
                        continue;
                    }

                    if (seat.getSeatColumn() == e.getStartColumn()) {
                        found = true;
                        seatService.save(
                            Seat.builder()
                                .auditorium(seat.getAuditorium())
                                .seatRow(seat.getSeatRow())
                                .seatColumn(seat.getSeatColumn() + 1)
                                .type(seat.getType())
                                .status(true)
                                .build()
                        );
                    }

                    // Nếu vị trí ghế tiếp theo không liền kề với ghế hiện tại, chỉ tăng ghế ở khoảng trống
                    if (i + 1 < currentRowSeats.size() && currentRowSeats.get(i + 1).getSeatColumn() > seat.getSeatColumn() + 1) {
                        Seat newSeat = Seat.builder()
                            .auditorium(seat.getAuditorium())
                            .seatRow(e.getSeatRow())
                            .seatColumn(seat.getSeatColumn() + 1)
                            .type(SeatType.NORMAL)
                            .status(true)
                            .build();
                        seatService.save(newSeat);
                        break;
                    }

                    // Nếu tất cả các ghế đều liền kề, chỉ tăng ghế nếu liên tiếp
                    if (found && seat.getSeatColumn() >= e.getStartColumn() + e.getPositions()) {
                        seat.setSeatColumn(seat.getSeatColumn() + 1);
                        seatService.save(seat);
                    }
                }

                // Nếu không tìm thấy ghế tại vị trí startColumn, tạo mới ghế tại vị trí đó
                if (!found) {
                    Seat newSeat = Seat.builder()
                        .auditorium(auditoriumService.getAuditoriumById(e.getAudId())) // Lấy auditorium từ service
                        .seatRow(e.getSeatRow())
                        .seatColumn(e.getStartColumn())
                        .type(SeatType.NORMAL) // Loại ghế mặc định
                        .status(true) // Đặt trạng thái mặc định
                        .build();

                    seatService.save(newSeat);
                }
            }
        }
    }



    public void seatUpdate(
        UpsertSeatRequest.SeatUpdate request
    ) {
        for (Integer id : request.getSeatIds()) {
            Seat seat = seatService.getSeatById(id);
            seat.setStatus(request.isStatus());
            seat.setType(enumService.getEnumValueByName(SeatType::valueOf, request.getType(), "SeatType"));
            seatService.save(seat);
        }
    }

    public void updateSeatDelete(
        Integer[] seatIds,
        boolean deleted
    ) {
        if (deleted) {
            for (Integer id : seatIds) {
                try {
                    seatService.delete(id);
                } catch (Exception e) {
                    Seat seat = seatService.getSeatById(id);
                    seat.setDeleted(true);
                    seatService.save(seat);
                }
            }
        } else {
            for (Integer id : seatIds) {
                Seat seat = seatService.getSeatById(id);
                seat.setDeleted(false);
                seatService.save(seat);
            }
        }
    }

    public void seatCreateForAuditorium(
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
