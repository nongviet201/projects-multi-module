package com.nongviet201.cinema.core.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpsertMovieRequest {
    String movieName;
    String description;
    boolean status;
    String trailer;
    Integer countryId;
    LocalDate releaseDate;
    List<Integer> genreIds;
    List<Integer> actorIds;
    List<Integer> directorIds;
}
