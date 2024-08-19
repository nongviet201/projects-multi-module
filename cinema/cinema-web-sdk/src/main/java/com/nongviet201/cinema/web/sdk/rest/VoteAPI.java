package com.nongviet201.cinema.web.sdk.rest;

import com.nongviet201.cinema.core.service.MovieService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/vote")
public class VoteAPI {
    private final MovieService movieService;

    @PutMapping("/put/{movieId}/{rating}")
    public ResponseEntity<?> voteMovie(
        @PathVariable int movieId,
        @PathVariable int rating,
        @CookieValue(value = "ratingMovieId", required = false) String ratedMovieId, // Đọc cookie nếu có
        HttpServletResponse response,
        HttpServletRequest request
    ) {
        // Kiểm tra cookie có chứa thông tin đánh giá của movieId hay không
        String cookieName = "ratingMovieId" + movieId;
        String existingRatingCookie = Arrays.stream(request.getCookies())
            .filter(cookie -> cookie.getName().equals(cookieName))
            .findFirst()
            .map(Cookie::getValue)
            .orElse(null);

        if (existingRatingCookie != null) {
            return new ResponseEntity<>("Bạn đã đánh giá trước đó rồi", HttpStatus.BAD_REQUEST);
        }

        double newRating = movieService.updateRating(movieId, rating);

        ResponseCookie cookie = ResponseCookie.from("ratingMovieId" + movieId, String.valueOf(true))
            .maxAge(365 * 24 * 60 * 60) // Thời hạn 1 năm
            .httpOnly(true)
            .path("/")
            .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return new ResponseEntity<>(newRating, HttpStatus.OK);
    }
}

