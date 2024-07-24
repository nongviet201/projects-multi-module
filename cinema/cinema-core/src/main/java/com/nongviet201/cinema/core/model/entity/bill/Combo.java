package com.nongviet201.cinema.core.model.entity.bill;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "combo")
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    @Column(columnDefinition = "TEXT")
    String description;
    String image;
    boolean status;
    BigDecimal price;
}
