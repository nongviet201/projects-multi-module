package com.nongviet201.cinema.core.model.entity;

import com.nongviet201.cinema.core.model.enums.UserRole;
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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto tăng id
    Integer id;
    @Column(nullable = false)
    String username;
    @Column(nullable = false)
    String password;
    String avatar;
    String email;
    String gender;
    LocalDate birthday;
    @Enumerated(EnumType.STRING)
    UserRole role;
    LocalDate createAt;
    LocalDate updateAt;
}
