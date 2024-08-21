package com.nongviet201.cinema.core.entity.cinema;

import com.nongviet201.cinema.core.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private int id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String address;
    private double lat;
    private double lng;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> manager;

}
