package com.nongviet201.cinema.core.entity.bill.coupon;

import com.nongviet201.cinema.core.entity.bill.Combo;
import com.nongviet201.cinema.core.entity.movie.Movie;
import com.nongviet201.cinema.core.model.enums.coupon.DiscountCouponType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coupon")
@SuperBuilder
public class Coupon extends BaseCoupon {

    // Ngày bắt đầu và kết thúc
    private LocalDate startDate;
    private LocalDate endDate;

    @ElementCollection
    @CollectionTable(name = "user_coupon",
    joinColumns = @JoinColumn(name = "coupon_id"))
    @Column(name = "user_id")
    private List<Integer> listUserIdUsed;

    public boolean isExpired() {
        return LocalDate.now().isAfter(this.getEndDate());
    }
}
