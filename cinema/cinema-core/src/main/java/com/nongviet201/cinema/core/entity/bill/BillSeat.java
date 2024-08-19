package com.nongviet201.cinema.core.entity.bill;

import com.nongviet201.cinema.core.entity.cinema.Seat;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name= "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;
}
