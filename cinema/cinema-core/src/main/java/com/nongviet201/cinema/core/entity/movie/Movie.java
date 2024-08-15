package com.nongviet201.cinema.core.entity.movie;

import com.nongviet201.cinema.core.converter.GenericConverter;
import com.nongviet201.cinema.core.model.enums.GraphicsType;
import com.nongviet201.cinema.core.model.enums.MovieAge;
import com.nongviet201.cinema.core.model.enums.TranslationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@ToString
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

    @Enumerated(EnumType.STRING)
    private MovieAge ageRequirement;

    private int duration;

    private double rating;

    private boolean status;

    @Column(columnDefinition = "TEXT")
    private LocalDate releaseDate;

    private LocalDate startAt;

    private LocalDate endAt;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private String producer;

    @Convert(converter = GraphicsTypeConverter.class)
    private List<GraphicsType> graphicsTypes;

    @Convert(converter = TranslationTypeConverter.class)
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

    @Converter(autoApply = true)
    public static class GraphicsTypeConverter extends GenericConverter<GraphicsType> {
        public GraphicsTypeConverter() {
            super(GraphicsType.class);
        }
    }

    @Converter(autoApply = true)
    public static class TranslationTypeConverter extends GenericConverter<TranslationType> {
        public TranslationTypeConverter() {
            super(TranslationType.class);
        }
    }
}
