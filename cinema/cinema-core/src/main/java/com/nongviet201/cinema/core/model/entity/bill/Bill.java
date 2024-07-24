package com.nongviet201.cinema.core.model.entity.bill;

import com.nongviet201.cinema.core.model.entity.User;
import com.nongviet201.cinema.core.model.entity.cinema.Showtime;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    boolean status;
    BigDecimal totalPrice;
    LocalDate createAt;
    LocalDate updateAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    Showtime showTime;
}
