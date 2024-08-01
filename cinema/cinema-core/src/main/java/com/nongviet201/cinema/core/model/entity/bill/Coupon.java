package com.nongviet201.cinema.core.model.entity.bill;

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
@Table(name = "coupon")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long discountPrice;
    private int limitAmount;
    private String code;
    private LocalDate startDate;
    private LocalDate endDate;
}
