package com.nongviet201.cinema.admin.sdk.rest;

import com.nongviet201.cinema.admin.sdk.request.UpsertSeatRequest;
import com.nongviet201.cinema.admin.sdk.service.AdminSeatService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/api/v1/seat")
public class SeatAPI {

    private final AdminSeatService adminSeatService;

    @GetMapping("/aud/{id}")
    public ResponseEntity<?> getSeatByAuditoriumId(
        @PathVariable Integer id
    ) {
        return new ResponseEntity<>(
            adminSeatService.getAllSeatByAuditoriumId(id),
            HttpStatus.OK
        );
    }

    @GetMapping("/aud-bin/{id}")
    public ResponseEntity<?> getSeatDeletedByAuditoriumId(
        @PathVariable Integer id
    ) {
        return new ResponseEntity<>(
            adminSeatService.getAllSeatDeletedByAuditoriumId(id),
            HttpStatus.OK
        );
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSeat(
        @RequestBody List<UpsertSeatRequest.SeatCreate> request
    ) {
        adminSeatService.createSeat(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create-row-and-column")
    public ResponseEntity<?> createRowAndColumn(
        @RequestBody UpsertSeatRequest.CreateRowAndColumn request
    ) {
        adminSeatService.createNewRowAndColumn(request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/order-verification/{audId}")
    public ResponseEntity<?> seatOrderVerification(
        @PathVariable Integer audId
    ) {
        adminSeatService.seatOrderVerification(audId);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("")
    public ResponseEntity<?> updateSeat(
       @RequestBody UpsertSeatRequest.SeatUpdate request
    ) {
        adminSeatService.seatUpdate(request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/delete")
    public ResponseEntity<?> deleteSeat(
        @RequestBody Integer[] seatIds
    ) {
        adminSeatService.updateSeatDelete(
            seatIds,
            true
        );
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/restore")
    public ResponseEntity<?> restoreSeat(
        @RequestBody Integer[] seatIds
    ) {
        adminSeatService.updateSeatDelete(
            seatIds,
            false
        );
        return ResponseEntity.noContent().build();
    }
}
