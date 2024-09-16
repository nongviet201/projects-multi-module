package com.nongviet201.cinema.admin.sdk.controller.service;

import com.nongviet201.cinema.core.entity.cinema.Cinema;
import com.nongviet201.cinema.core.model.enums.movie.GraphicsType;
import com.nongviet201.cinema.core.model.enums.movie.TranslationType;
import com.nongviet201.cinema.core.model.enums.showtime.ScreeningTimeType;
import com.nongviet201.cinema.core.service.CinemaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminShowtimeControllerService {

    private final CinemaService cinemaService;

    public List<Cinema> getAllCinemas() {
        return cinemaService.getAllCinemaByDeleted(false);
    }

    public void commonShowtimeAttributes(Model model) {
        model.addAttribute("cinema", getAllCinemas());
        model.addAttribute("screeningTimeTypes", ScreeningTimeType.values());
        model.addAttribute("graphicTypes", GraphicsType.values());
        model.addAttribute("translationTypes", TranslationType.values());
    }
}
