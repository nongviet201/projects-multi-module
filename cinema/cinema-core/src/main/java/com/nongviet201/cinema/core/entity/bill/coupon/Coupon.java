package com.nongviet201.cinema.core.entity.bill.coupon;

import com.nongviet201.cinema.core.entity.bill.Combo;
import com.nongviet201.cinema.core.entity.movie.Movie;
import com.nongviet201.cinema.core.model.enums.coupon.DiscountCouponType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "coupon")
public class Coupon extends BaseCoupon {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie; // nếu sử dụng sẽ chỉ định tới 1 movie cố định

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "combo_id")
    private Combo combo; // nếu sử dụng sẽ chỉ định tới 1 movie cố định

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_coupon_rule_id")
    private BaseCouponRule baseCouponRule;

    @ElementCollection
    @CollectionTable(name = "user_coupon",
    joinColumns = @JoinColumn(name = "coupon_id"))
    @Column(name = "user_id")
    private List<Integer> listUserIdUsed;
}
