package com.nongviet201.cinema.core.model.entity.bill;

import com.nongviet201.cinema.core.model.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "coupon_user")
public class CouponUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDate usedAt;

    @OneToOne
    @JoinColumn(name = "coupon_id")
    Coupon coupon;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
}
