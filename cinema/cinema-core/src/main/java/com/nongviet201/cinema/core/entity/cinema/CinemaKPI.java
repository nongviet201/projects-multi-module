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
@Table(name = "cinema_kpi")
public class CinemaKPI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer month;
    private Integer year;
    private Integer totalTicketSold;
    private Long totalRevenue;
    private Long targetRevenue;
    private boolean achieved;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

}
