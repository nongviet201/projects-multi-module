package com.nongviet201.cinema.admin.sdk.rest;

import com.nongviet201.cinema.admin.sdk.controller.service.AdminCinemaControllerService;
import com.nongviet201.cinema.admin.sdk.request.UpsertAuditoriumRequest;
import com.nongviet201.cinema.admin.sdk.request.UpsertCinemaRequest;
import com.nongviet201.cinema.admin.sdk.response.AdminCinemaMarkerResponse;
import com.nongviet201.cinema.admin.sdk.response.AdminCinemaRevenueResponse;
import com.nongviet201.cinema.admin.sdk.service.AdminCinemaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/api/v1/cinema")
public class CinemaAPI {

    private final AdminCinemaControllerService cinemaControllerService;
    private final AdminCinemaService adminCinemaService;

    @GetMapping("/get/all-marker")
    public List<AdminCinemaMarkerResponse> getCinemaMarker() {
        return cinemaControllerService.getAllCinemaMarker();
    }

    @GetMapping("/get/all-revenue")
    public List<AdminCinemaRevenueResponse> getAllCinemaRevenue(
        @RequestParam(value = "time", required = false, defaultValue = "week") String time
    ) {
        return cinemaControllerService.getAllCinemaRevenue(time);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(
        @PathVariable int id,
        @RequestBody UpsertCinemaRequest.Cinema request
    ) {
        adminCinemaService.updateCinema(id, request);
        return ResponseEntity.ok(request);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(
        @PathVariable int id
    ) {
        adminCinemaService.updateDeletedCinema(id, true);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restoreItem(
        @PathVariable int id
    ) {
        adminCinemaService.updateDeletedCinema(id, false);
        return ResponseEntity.noContent().build();
    }

}
