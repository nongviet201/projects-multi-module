package com.nongviet201.cinema.web.sdk.controller.view;

import com.nongviet201.cinema.core.service.BillService;
import com.nongviet201.cinema.core.service.MovieService;
import com.nongviet201.cinema.core.service.ShowtimeService;
import com.nongviet201.cinema.web.sdk.controller.service.WebBillControllerService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
public abstract class WebController {
    private final MovieService movieService;
    private final ShowtimeService showtimeService;
    private final WebBillControllerService billControllerService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute(
            "movies",
            movieService.getAllPublishMoviesOrderByReleaseDate()
        );
        return "index";
    }

    @GetMapping("/movie/{slug}")
    public String infoMoviePage(@PathVariable("slug") String slug, Model model) {
        model.addAttribute(
            "movie",
            movieService.getPublishMovieBySlug(slug)
        );
        model.addAttribute(
            "newMovies",
            movieService.getAllPublishMoviesOrderByReleaseDate()
        );
        return "movie/detail";
    }

    @GetMapping("/booking")
    public String getBookingPage(
            @RequestParam(value = "showtime", required = false) Integer showtimeId,
            @RequestParam(value = "vnp_TxnRef", required = false) Integer bill,
            @RequestParam(value = "vnp_ResponseCode", required = false) String code,
            Model model
    ) {
        if (showtimeId!= null) {
            model.addAttribute(
                "showtime",
                showtimeService.getShowtimeById(showtimeId)
            );
        }
        if (bill!= null) {
            if (code.equals("00")) {
                model.addAttribute(
                    "code",
                    code
                );
                model.addAttribute(
                    "bill",
                    billControllerService.updateBill(bill)
                );
            } else {
                model.addAttribute(
                    "code",
                    code
                );
                model.addAttribute(
                    "bill",
                    billControllerService.getBillById(bill)
                );
            }
        }
        return "booking/booking";
    }
}
