package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.request.UpsertShowtimeRequest;
import com.nongviet201.cinema.core.entity.cinema.Showtime;
import com.nongviet201.cinema.core.model.enums.movie.GraphicsType;
import com.nongviet201.cinema.core.model.enums.movie.TranslationType;
import com.nongviet201.cinema.core.model.enums.showtime.ScreeningTimeType;
import com.nongviet201.cinema.core.service.AuditoriumService;
import com.nongviet201.cinema.core.service.MovieScheduleService;
import com.nongviet201.cinema.core.service.ShowtimeService;
import com.nongviet201.cinema.core.utils.EnumService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.nongviet201.cinema.core.utils.DateTimeUtils.parseDate;
import static com.nongviet201.cinema.core.utils.DateTimeUtils.parseTime;

@Service
@AllArgsConstructor
public class AdminShowtimeService {

    private final ShowtimeService showtimeService;
    private final MovieScheduleService movieScheduleService;
    private final AuditoriumService auditoriumService;
    private final EnumService enumService;

    public Showtime getShowtimeById(
        Integer id
    ) {
        return showtimeService.getShowtimeById(id);
    }

    public List<Showtime> getDataFiller(
        UpsertShowtimeRequest.GetDataFiller request
    ) {
        LocalDate formDate = parseDate(request.getFormDate());

        if (!request.getToDate().isEmpty()) {
            LocalDate toDate = parseDate(request.getToDate());

            return showtimeService.getDataFiller(
                formDate,
                toDate,
                request.getCinemaId(),
                request.getMovieId()
            );
        }

        return showtimeService.getDataFiller(
            formDate,
            formDate,
            request.getCinemaId(),
            request.getMovieId()
        );
    }

    public boolean checkDataCreate(
        UpsertShowtimeRequest.CreateShowtime request
    ) {

        // kiểm tra thời gian bắt đầu và kết thúc có bị trùng không
        boolean check = showtimeService.checkDataCreate(
            request.getAuditoriumId(),
            request.getMovieScheduleId(),
            parseDate(request.getScreeningDate()),
            parseTime(request.getStartTime()),
            parseTime(request.getEndTime())
        );

        return !check;
    }

    public void createShowtime(
        UpsertShowtimeRequest.CreateShowtime request
    ) {

        if (checkDataCreate(request)) {
            showtimeService.save(
                Showtime.builder()
                    .movieSchedule(movieScheduleService.findById(request.getMovieScheduleId()))
                    .auditorium(auditoriumService.getAuditoriumById(request.getAuditoriumId()))
                    .translationType(enumService.getEnumValueByName(TranslationType::valueOf, request.getTranslationType(), "TranslationType"))
                    .graphicsType(enumService.getEnumValueByName(GraphicsType::valueOf, request.getGraphicsType(), "GraphicsType"))
                    .screeningTimeType(enumService.getEnumValueByName(ScreeningTimeType::valueOf, request.getScreeningTimeType(), "ScreeningTimeType"))
                    .auditoriumType(auditoriumService.getAuditoriumById(request.getAuditoriumId()).getAuditoriumType())
                    .startTime(parseTime(request.getStartTime()))
                    .endTime(parseTime(request.getEndTime()))
                    .screeningDate(parseDate(request.getScreeningDate()))
                    .build()
            );
        } else {
            throw new IllegalArgumentException("Thời gian suất chiếu bạn chọn bị trùng lặp");
        }
    }

    public void delete(
        Integer id
    ) {
        try {
            showtimeService.deleteById(id);
        } catch (Exception e) {
            Showtime showtime = showtimeService.getShowtimeById(id);
            showtime.setDeleted(true);
            showtimeService.save(showtime);
        }
    }
}
