package com.nongviet201.cinema.admin.sdk.rest;

import com.nongviet201.cinema.admin.sdk.request.UpsertMovieScheduleRequest;
import com.nongviet201.cinema.admin.sdk.service.AdminMovieScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/schedule")
@AllArgsConstructor
public class MovieScheduleAPI {

    private final AdminMovieScheduleService adminMovieScheduleService;

    @PostMapping("/create")
    public ResponseEntity<?> create(
       @RequestBody UpsertMovieScheduleRequest request
    ) {
        adminMovieScheduleService.create(request);
        return ResponseEntity.ok("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
        @PathVariable Integer id,
        @RequestBody UpsertMovieScheduleRequest request
    ) {
        adminMovieScheduleService.update(
            id,
            request
        );
        return ResponseEntity.ok("Updated");
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(
            adminMovieScheduleService.getAllMovieSchedule()
        );
    }

    @GetMapping("/getMovieAvailable")
    public ResponseEntity<?> getMovieAvailable() {
        return ResponseEntity.ok(
            adminMovieScheduleService.getAllMoviesAvailable()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
        @PathVariable Integer id
    ) {
        adminMovieScheduleService.deleteById(id);
        return ResponseEntity.ok("Deleted!");
    }
}
