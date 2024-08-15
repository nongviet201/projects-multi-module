package com.nongviet201.cinema.core.entity.bill.coupon;

import com.nongviet201.cinema.core.entity.cinema.Cinema;
import com.nongviet201.cinema.core.entity.movie.Genre;
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
import java.time.LocalTime;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "base_coupon_rule")
public class BaseCouponRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ruleName;
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

    private boolean status;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

}
