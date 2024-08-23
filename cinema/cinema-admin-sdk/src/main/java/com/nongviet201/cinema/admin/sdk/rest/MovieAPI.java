package com.nongviet201.cinema.admin.sdk.rest;

import com.nongviet201.cinema.admin.sdk.request.UpsertMetaDataRequest;
import com.nongviet201.cinema.admin.sdk.request.UpsertMovieRequest;
import com.nongviet201.cinema.admin.sdk.service.AdminMovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/movie")
@AllArgsConstructor
public class MovieAPI {

    private final AdminMovieService adminMovieService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(
        @PathVariable int id
    ) {
        adminMovieService.updateDeletedMovie(id, true);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/meta-data/{id}/{type}")
    public ResponseEntity<?> createMetaData(
        @PathVariable int id,
        @PathVariable String type
    ) {
        adminMovieService.deleteMetaData(id, type);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMovie(
        @RequestBody UpsertMovieRequest request
    ) {
        adminMovieService.createMovie(request);
        return ResponseEntity.ok(request);
    }

    @PostMapping("/meta-data/create")
    public ResponseEntity<?> createMetaData(
        @RequestBody UpsertMetaDataRequest request
    ) {
        adminMovieService.createMetaData(request);
        return ResponseEntity.ok(request);
    }

    @PutMapping("/meta-data/edit/{id}")
    public ResponseEntity<?> editMetaData(
        @PathVariable int id,
        @RequestBody UpsertMetaDataRequest request
    ) {
        adminMovieService.editMetaData(id, request);
        return ResponseEntity.ok(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(
        @PathVariable int id,
        @RequestBody UpsertMovieRequest request
    ) {
        adminMovieService.updateMovie(id, request);
        return ResponseEntity.ok(request);
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restoreMovie(
        @PathVariable int id
    ) {
        adminMovieService.updateDeletedMovie(id, false);
        return ResponseEntity.noContent().build();
    }

}
