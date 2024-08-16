package com.nongviet201.cinema.core.entity.bill.coupon;

import com.nongviet201.cinema.core.entity.bill.Combo;
import com.nongviet201.cinema.core.entity.cinema.Cinema;
import com.nongviet201.cinema.core.entity.movie.Genre;
import com.nongviet201.cinema.core.entity.movie.Movie;
import com.nongviet201.cinema.core.model.enums.cinema.AuditoriumType;
import com.nongviet201.cinema.core.model.enums.cinema.SeatType;
import com.nongviet201.cinema.core.model.enums.coupon.OfferCategory;
import com.nongviet201.cinema.core.model.enums.coupon.UserType;
import com.nongviet201.cinema.core.model.enums.movie.GraphicsType;
import com.nongviet201.cinema.core.model.enums.showtime.DayType;
import com.nongviet201.cinema.core.model.enums.showtime.ScreeningTimeType;
import com.nongviet201.cinema.core.model.enums.user.UserRank;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "coupon_roles")
public class CouponRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ruleName;
    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private AuditoriumType auditoriumType;

    @Enumerated(EnumType.STRING)
    private DayType dayType;

    @Enumerated(EnumType.STRING)
    private GraphicsType graphicsType;

    @Enumerated(EnumType.STRING)
    private ScreeningTimeType screeningTimeType;

    @Enumerated(EnumType.STRING)
    private UserRank userRank;

    @Enumerated(EnumType.STRING)
    private OfferCategory offerCategory; // ticket hay combo

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @Enumerated(EnumType.STRING)
    private UserType userType; // dựa trên kiểu người dùng

    private boolean status;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie; // nếu sử dụng sẽ chỉ định tới 1 movie cố định

    @ManyToOne
    @JoinColumn(name = "combo_id")
    private Combo combo; // nếu sử dụng sẽ chỉ định tới 1 movie cố định

}
