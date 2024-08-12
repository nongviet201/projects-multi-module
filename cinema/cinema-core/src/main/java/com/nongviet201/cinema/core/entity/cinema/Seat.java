package com.nongviet201.cinema.core.entity.cinema;


import com.nongviet201.cinema.core.model.enums.SeatType;
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
    @Enumerated(EnumType.STRING)
    private SeatType type;

    @ManyToOne
    @JoinColumn(name= "auditorium_id")
    private Auditorium auditorium;

}
