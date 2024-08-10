package com.nongviet201.cinema.core.model.entity.bill;

import com.nongviet201.cinema.core.model.entity.cinema.Seat;
import com.nongviet201.cinema.core.model.entity.cinema.Showtime;
import com.nongviet201.cinema.core.model.entity.user.User;
import com.nongviet201.cinema.core.model.enums.ReservationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private ReservationType status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name="showtime_id")
    private Showtime showTime;
}
