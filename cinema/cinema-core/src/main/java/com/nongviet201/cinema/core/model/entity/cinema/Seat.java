package com.nongviet201.cinema.core.model.entity.cinema;


import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean status;
    private String seatRow;
    private int seatColumn;

    @ManyToOne
    @JoinColumn(name= "auditorium_id")
    private Auditorium auditorium;

    @ManyToOne
    @JoinColumn(name = "seat_type_id")
    private SeatType type;
}
