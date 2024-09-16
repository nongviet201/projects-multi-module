package com.nongviet201.cinema.admin.sdk.rest;

import com.nongviet201.cinema.admin.sdk.request.UpsertShowtimeRequest;
import com.nongviet201.cinema.admin.sdk.service.AdminShowtimeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/showtime")
@AllArgsConstructor
public class ShowTimeAPI {

    private final AdminShowtimeService adminShowtimeService;

    @PostMapping ("/getData")
    public ResponseEntity<?> getShowTimeData(
        @RequestBody UpsertShowtimeRequest.GetDataFiller request
    ) {

        return ResponseEntity.ok(
            adminShowtimeService.getDataFiller(request)
        );
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> getShowTimeData(
        @PathVariable Integer id
    ) {
        adminShowtimeService.delete(id);
        return ResponseEntity.ok("success");
    }

    @PostMapping ("/create")
    public ResponseEntity<?> getShowTimeData(
        @RequestBody UpsertShowtimeRequest.CreateShowtime request
    ) {
        adminShowtimeService.createShowtime(request);
        return ResponseEntity.ok("success");
    }

    @PostMapping ("/checkDataCreate")
    public ResponseEntity<?> checkDataCreate(
        @RequestBody UpsertShowtimeRequest.CreateShowtime request
    ) {
        return ResponseEntity.ok(
            adminShowtimeService.checkDataCreate(request)
        );
    }

}
