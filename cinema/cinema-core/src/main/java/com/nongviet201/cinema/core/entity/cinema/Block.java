package com.nongviet201.cinema.core.entity.cinema;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "block")
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seatRow;
    private int startColumn;
    private int endColumn;
    private int positions;

    @ManyToOne
    @JoinColumn(name= "auditorium_id")
    private Auditorium auditorium;

}
