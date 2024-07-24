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
@Table(name = "cinema")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    @Column(columnDefinition = "TEXT")
    String address;

    @ManyToOne
    @JoinColumn(name = "city_id")
    City city;
}
