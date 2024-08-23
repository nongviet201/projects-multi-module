package com.nongviet201.cinema.core.entity.movie;

import com.nongviet201.cinema.core.model.enums.movie.MovieScheduleStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movie_schedule")
public class MovieSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto tăng id
    private Integer id;
    private LocalDate startAt;
    private LocalDate endAt;

    @Enumerated(EnumType.STRING)
    private MovieScheduleStatus movieScheduleStatus;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
