package com.nongviet201.cinema.core.entity.user;

import com.nongviet201.cinema.core.entity.cinema.Cinema;
import com.nongviet201.cinema.core.model.enums.user.EmployeePosition;
import com.nongviet201.cinema.core.model.enums.user.EmployeeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private EmployeePosition position;
    private EmployeeStatus status;
    private Long salary;

    private LocalDate joinDate;
    private LocalDate endDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

}
