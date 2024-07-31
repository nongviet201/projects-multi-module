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
    int id;
    String name;
    int totalRowChair;
    int totalColumnChair;
    @Enumerated(EnumType.STRING)
    AuditoriumType auditoriumType;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    Cinema cinema;
}
