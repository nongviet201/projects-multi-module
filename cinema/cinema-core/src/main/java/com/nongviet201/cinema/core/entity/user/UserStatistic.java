package com.nongviet201.cinema.core.entity.user;

import com.nongviet201.cinema.core.model.enums.UserRank;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_statistics")
public class UserStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer points;
    private Long totalSpending;

    @Enumerated(EnumType.STRING)
    private UserRank userRank;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
