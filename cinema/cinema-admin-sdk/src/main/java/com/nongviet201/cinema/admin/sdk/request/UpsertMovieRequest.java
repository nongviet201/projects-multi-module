package com.nongviet201.cinema.admin.sdk.request;

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
    String name;
    String slug;
    String description;
    String poster;
    String bannerImg;
    String trailer;
    String AgeRequirement;
    Integer duration;
    String producer;
    Double rating;
    Integer ratingCount;
    LocalDate releaseDate;
    boolean status;
    List<String> graphicTypes;
    List<String> translationTypes;
    List<Integer> countryIds;
    List<Integer> genreIds;
    List<Integer> actorIds;
    List<Integer> directorIds;
}