package com.nongviet201.cinema.web.sdk.controller.view;

import com.nongviet201.cinema.core.model.enums.PostType;
import com.nongviet201.cinema.core.service.MovieService;
import com.nongviet201.cinema.web.sdk.controller.service.*;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@AllArgsConstructor
public abstract class WebController {
    private final MovieService movieService;
    private final WebShowtimeControllerService webShowtimeControllerService;
    private final WebBillControllerService webBillControllerService;
    private final WebUserControllerService webUserControllerService;
    private final WebVerifyService verifyService;
    private final WebPostsControllerService webPostsControllerService;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute(
            "movies",
            movieService.getAllPublishMoviesOrderByRating()
        );
        model.addAttribute(
            "posts",
            webPostsControllerService.getPostsByTypePage(
                PostType.REVIEW,
                0,
                4
            )
        );
        return "index";
    }

    @GetMapping("/movie/{id}")
    public String infoMoviePage(
        @PathVariable("id") int id,
        Model model
    ) {
        model.addAttribute(
            "movie",
            movieService.getPublishMovieById(id)
        );
        model.addAttribute(
            "showtimes",
            webShowtimeControllerService.getAllShowTimesByMovieIdAndDate(id, LocalDate.now())
        );
        return "movie/detail";
    }

    @GetMapping("/booking")
    public String getBookingPage(
            @RequestParam(value = "showtime", required = false) Integer showtimeId,
            @RequestParam(value = "billId", required = false) Integer billId,
            Model model
    ) {
        if (showtimeId!= null) {
            model.addAttribute(
                "showtime",
                webShowtimeControllerService.getShowtimeById(showtimeId)
            );
        }
        if (billId!= null) {
            model.addAttribute(
                "bill",
                webBillControllerService.getBillById(billId)
            );
        }
        return "booking/booking";
    }

    @GetMapping("/posts")
    public String getBlogsPage(
        @RequestParam(value = "id", required = false) Integer id,
        @RequestParam(value = "type", required = false, defaultValue = "review") String type,
        Model model
    ) {
        if (id!= null) {
            model.addAttribute(
                "post",
                webPostsControllerService.getPostsById(id)
            );
            return "post/detail";
        }
        if (type != null) {
            model.addAttribute(
                "type",
                type
            );
            return "post/index";
        }
        return "post/index";
    }


    @GetMapping("/verification")
    public String getVerificationPage(
        @RequestParam(value = "token", required = false) String token,
        @RequestParam(value = "tokenForgotPassword", required = false) String tokenForgotPassword,
        Model model
    ) {
        if (token!= null) {
            model.addAttribute(
                "verifyResponse",
                verifyService.verifyRegister(token)
            );
        }
        if (tokenForgotPassword!= null) {
            model.addAttribute(
                "verifyResponse",
                verifyService.verifyForgotPassword(tokenForgotPassword)
            );
        }
        return "pages/verify";
    }

    @GetMapping("/user")
    public String getUserPage(
        @RequestParam(value = "stage", defaultValue = "2") Integer stage,
        Model model
    ) {
        model.addAttribute(
            "stage",
            stage
        );
        model.addAttribute(
            "userStatistic",
            webUserControllerService.getUserStatistic()
        );
        return "user/user";
    }
}
