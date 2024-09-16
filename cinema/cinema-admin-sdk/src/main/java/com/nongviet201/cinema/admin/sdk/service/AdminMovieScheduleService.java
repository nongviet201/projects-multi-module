package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.request.UpsertMovieScheduleRequest;
import com.nongviet201.cinema.core.entity.movie.Movie;
import com.nongviet201.cinema.core.entity.movie.MovieSchedule;
import com.nongviet201.cinema.core.model.enums.movie.MovieScheduleStatus;
import com.nongviet201.cinema.core.service.MovieScheduleService;
import com.nongviet201.cinema.core.service.MovieService;
import com.nongviet201.cinema.core.utils.EnumService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nongviet201.cinema.core.utils.DateTimeUtils.parseDate;
import static java.time.LocalDate.now;

@Service
@AllArgsConstructor
public class AdminMovieScheduleService {

    private final MovieService movieService;
    private final EnumService enumService;
    private final MovieScheduleService movieScheduleService;

    public void create(
        UpsertMovieScheduleRequest.CreateAndUpdate request
    ) {

        if (movieScheduleService.findByMovieId(request.getMovieId()) != null) {
            throw new RuntimeException("Phim bạn chọn đã có lịch chiếu!");
        }

        Movie movie = movieService.getMovieById(request.getMovieId());

        movieScheduleService.save(
            MovieSchedule.builder()
                .movie(movie)
                .startAt(request.getStartDate())
                .endAt(request.getEndDate())
                .status(enumService.getEnumValueByName(MovieScheduleStatus::valueOf, request.getStatus(), "MovieScheduleStatus"))
                .createdAt(now())
                .updatedAt(now())
                .build()
        );
    }

    public void update(
        Integer id,
        UpsertMovieScheduleRequest.CreateAndUpdate request
    ) {
        MovieSchedule movieSchedule = movieScheduleService.findById(id);

        if (movieSchedule == null) {
            throw new RuntimeException("Lịch chiếu không tồn tại!");
        }

        movieSchedule.setStartAt(request.getStartDate());
        movieSchedule.setEndAt(request.getEndDate());
        movieSchedule.setStatus(enumService.getEnumValueByName(MovieScheduleStatus::valueOf, request.getStatus(), "MovieScheduleStatus"));
        movieSchedule.setUpdatedAt(now());

        movieScheduleService.save(movieSchedule);
    }

    public List<MovieSchedule> getAllMovieSchedule() {
        return movieScheduleService.getAllMovieSchedule();
    }

    public void deleteById(
        Integer id
    ) {
        movieScheduleService.deleteById(id);
    }

    public List<Movie> getAllMoviesAvailable() {
        List<Movie> movies = movieService.getAllPublishMoviesOrderByRating();

        List<MovieSchedule> schedules = movieScheduleService.getAllMovieSchedule();

        List<Integer> scheduledMovieIds = schedules.stream()
            .map(schedule -> schedule.getMovie().getId())
            .toList();

        return movies.stream()
            .filter(movie -> !scheduledMovieIds.contains(movie.getId()))
            .toList();
    }

    public List<MovieSchedule> getAllMovieScheduleByDate(
        UpsertMovieScheduleRequest.GetMovieScheduleFiller request
    ) {
        if (request.getFormDate().isEmpty()) {
            return null;
        }

        if (request.getToDate().isEmpty()) {
            return movieScheduleService.getMovieScheduleByDate(
                parseDate(request.getFormDate()),
                parseDate(request.getFormDate())
            );
        }

        return movieScheduleService.getMovieScheduleByDate(
            parseDate(request.getFormDate()),
            parseDate(request.getToDate())
        );
    }

    public List<MovieSchedule> getMoviesNonExpiredByDate(
        String date
    ) {
        return movieScheduleService.getMovieScheduleOnDateAndNotExpired(
            parseDate(date)
        );
    }
}
