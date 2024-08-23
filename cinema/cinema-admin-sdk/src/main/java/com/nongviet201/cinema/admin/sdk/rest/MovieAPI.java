package com.nongviet201.cinema.admin.sdk.rest;

import com.nongviet201.cinema.admin.sdk.service.AdminMovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api/v1/movie")
@AllArgsConstructor
public class MovieAPI {

    private final AdminMovieService adminMovieService;

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteMovie(
        @PathVariable int id
    ) {
        adminMovieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
