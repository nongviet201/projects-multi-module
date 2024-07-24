package com.nongviet201.cinema.core.rest;


import com.nongviet201.cinema.core.model.entity.movie.Movie;
import com.nongviet201.cinema.core.model.request.UpsertMovieRequest;
import com.nongviet201.cinema.core.service.MovieService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/movies")
@AllArgsConstructor
public class MovieApi {
    private final MovieService movieService;

    //Tạo Movies
    @PostMapping
    public ResponseEntity<?> createMovie(
        @Valid
        @RequestBody
        UpsertMovieRequest request
    ){
        Movie movie = movieService.createMovie(request);
        return new ResponseEntity<>(movie, HttpStatus.CREATED); //201
    }

}
