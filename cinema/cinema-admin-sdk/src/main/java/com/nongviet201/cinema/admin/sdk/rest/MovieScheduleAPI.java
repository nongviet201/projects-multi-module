package com.nongviet201.cinema.admin.sdk.rest;

import com.nongviet201.cinema.admin.sdk.request.UpsertMovieScheduleRequest;
import com.nongviet201.cinema.admin.sdk.service.AdminMovieScheduleService;
import com.nongviet201.cinema.core.entity.movie.MovieSchedule;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api/v1/schedule")
@AllArgsConstructor
public class MovieScheduleAPI {

    private final AdminMovieScheduleService adminMovieScheduleService;

    @PostMapping("/create")
    public ResponseEntity<?> create(
       @RequestBody UpsertMovieScheduleRequest.CreateAndUpdate request
    ) {
        adminMovieScheduleService.create(request);
        return ResponseEntity.ok("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
        @PathVariable Integer id,
        @RequestBody UpsertMovieScheduleRequest.CreateAndUpdate request
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

    @PostMapping("/getMovieScheduleByDate")
    public ResponseEntity<?> getMovieScheduleByDate(
        @RequestBody UpsertMovieScheduleRequest.GetMovieScheduleFiller request
    ) {
        return ResponseEntity.ok(
            adminMovieScheduleService.getAllMovieScheduleByDate(request)
        );
    }

    @GetMapping("/getMovieScheduleNonExpiredByDate")
    public ResponseEntity<?> getMovieScheduleByDate(
        @RequestParam(value = "date") String date
    ) {
        return ResponseEntity.ok(
            adminMovieScheduleService.getMoviesNonExpiredByDate(date)
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
