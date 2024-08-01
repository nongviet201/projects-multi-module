package com.nongviet201.cinema.core.model.entity.bill;

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
@Table(name = "bill_combo")
public class BillCombo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private long price;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name= "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "combo_id")
    private Combo combo;
}
