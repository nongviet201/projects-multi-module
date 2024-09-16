package com.nongviet201.cinema.core.entity.cinema;

import com.nongviet201.cinema.core.entity.movie.Movie;
import com.nongviet201.cinema.core.entity.movie.MovieSchedule;
import com.nongviet201.cinema.core.model.enums.cinema.AuditoriumType;
import com.nongviet201.cinema.core.model.enums.movie.GraphicsType;
import com.nongviet201.cinema.core.model.enums.showtime.ScreeningTimeType;
import com.nongviet201.cinema.core.model.enums.movie.TranslationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "showtime")
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private ScreeningTimeType screeningTimeType;
    @Enumerated(EnumType.STRING)
    private GraphicsType graphicsType;
    @Enumerated(EnumType.STRING)
    private AuditoriumType auditoriumType;
    @Enumerated(EnumType.STRING)
    private TranslationType translationType;

    private LocalDate screeningDate;
    private LocalTime startTime;
    private LocalTime endTime;

    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "movie_schedule_id")
    private MovieSchedule movieSchedule;

    @ManyToOne
    @JoinColumn(name= "auditorium_id")
    private Auditorium auditorium;
}
