package com.nongviet201.cinema.web.sdk.rest;

import com.nongviet201.cinema.core.service.PostService;
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
@RequestMapping("/api/v1/post")
public class PostAPI {
    private final PostService postService;

    @PutMapping("/view/{postId}")
    public ResponseEntity<?> voteMovie(
        @PathVariable int postId,
        @CookieValue(value = "postView", required = false) String postView, // Đọc cookie nếu có
        HttpServletResponse response,
        HttpServletRequest request
    ) {
        String cookieName = "postView" + postId;
        String existingRatingCookie = Arrays.stream(request.getCookies())
            .filter(cookie -> cookie.getName().equals(cookieName))
            .findFirst()
            .map(Cookie::getValue)
            .orElse(null);

        if (existingRatingCookie != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        postService.updateViewCount(postId);

        ResponseCookie cookie = ResponseCookie.from("postView" + postId, String.valueOf(true))
            .maxAge(365 * 24 * 60 * 60) // Thời hạn 1 năm
            .httpOnly(true)
            .path("/")
            .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
