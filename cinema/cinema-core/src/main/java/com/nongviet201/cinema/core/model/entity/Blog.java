package com.nongviet201.cinema.core.model.entity;

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
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto tăng id
    Integer id;
    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String slug;
    @Column(columnDefinition = "TEXT")
    String description;
    @Column(columnDefinition = "TEXT")
    String content;
    String thumbnail;
    boolean status;
    LocalDate createdAt;
    LocalDate updateAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
