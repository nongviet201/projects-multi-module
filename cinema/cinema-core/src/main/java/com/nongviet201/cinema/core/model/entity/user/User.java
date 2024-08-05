package com.nongviet201.cinema.core.model.entity.user;

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
    private Integer id;
    @Column(nullable = false)
    private String fullName;
    private String email;
    @Column(nullable = false)
    private String password;
    private String avatar;
    private String phoneNumber;
    private String gender;
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private boolean enabled;
    private LocalDate createAt;
    private LocalDate updateAt;
}
