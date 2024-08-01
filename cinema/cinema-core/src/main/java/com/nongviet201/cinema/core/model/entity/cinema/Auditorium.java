package com.nongviet201.cinema.core.model.entity.cinema;

import com.nongviet201.cinema.core.model.enums.AuditoriumType;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "auditorium")
public class Auditorium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String name;
    private int totalRowChair;
    private int totalColumnChair;
    @Enumerated(EnumType.STRING)
    private AuditoriumType auditoriumType;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
}
