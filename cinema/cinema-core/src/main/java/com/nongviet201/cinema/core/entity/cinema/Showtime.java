package com.nongviet201.cinema.core.entity.cinema;

import com.nongviet201.cinema.core.entity.movie.Movie;
import com.nongviet201.cinema.core.model.enums.AuditoriumType;
import com.nongviet201.cinema.core.model.enums.GraphicsType;
import com.nongviet201.cinema.core.model.enums.ScreeningTimeType;
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

    private LocalDate screeningDate;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "movies_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name= "auditorium_id")
    private Auditorium auditorium;
}
