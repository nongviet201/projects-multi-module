package com.nongviet201.cinema.core.entity.cinema;


import com.nongviet201.cinema.core.model.enums.cinema.SeatType;
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
    private String seatRow;
    private int seatColumn;
    @Enumerated(EnumType.STRING)
    private SeatType type;

    private boolean status;
    private boolean block;
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name= "auditorium_id")
    private Auditorium auditorium;

}
