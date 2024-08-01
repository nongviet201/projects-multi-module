package com.nongviet201.cinema.core.model.entity.bill;


import com.nongviet201.cinema.core.model.entity.User;
import com.nongviet201.cinema.core.model.entity.cinema.Showtime;
import com.nongviet201.cinema.payment.vnpay.response.PaymentResponse;
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
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean status;
    private long totalPrice;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Integer responseCode;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private Showtime showTime;
}
