package com.nongviet201.cinema.core.entity.movie;

import com.nongviet201.cinema.core.model.enums.movie.GraphicsType;
import com.nongviet201.cinema.core.model.enums.movie.MovieAge;
import com.nongviet201.cinema.core.model.enums.movie.TranslationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto tăng id
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String poster;

    @Column(columnDefinition = "TEXT")
    private String bannerImg;

    private String trailer;

    private MovieAge ageRequirement;

    private int duration;

    private double rating;
    private int ratingCount;
    private double totalRatings;

    private boolean status;

    @Column(columnDefinition = "TEXT")
    private LocalDate releaseDate;

    private LocalDate startAt;

    private LocalDate endAt;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private String producer;

    // Sử dụng @ElementCollection để lưu trữ danh sách GraphicsType
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "movie_graphics_types", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "graphics_type")
    @Enumerated(EnumType.STRING)
    private List<GraphicsType> graphicsTypes;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "movie_translation_types", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "translation_type")
    @Enumerated(EnumType.STRING) // Lưu enum dưới dạng chuỗi
    private List<TranslationType> translationTypes;

    @ManyToOne
    @JoinColumn(name = "countries_id")
    private Country country;

    @ManyToMany
    @JoinTable(
        name = "movie_genres",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(
        name = "movie_director",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    private List<Director> directors;

    @ManyToMany
    @JoinTable(
        name = "movie_actor",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors;
}
