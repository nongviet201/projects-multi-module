package com.nongviet201.cinema.core.model.entity.bill;

import com.nongviet201.cinema.core.model.entity.cinema.Seat;
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
@Table(name = "bill_seat")
public class BillSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long price;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name= "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;
}
