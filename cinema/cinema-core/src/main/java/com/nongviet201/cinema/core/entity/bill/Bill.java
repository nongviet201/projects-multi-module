package com.nongviet201.cinema.core.entity.bill;

import com.nongviet201.cinema.core.entity.user.User;
import com.nongviet201.cinema.core.entity.cinema.Showtime;
import com.nongviet201.cinema.payment.vnpay.code.ResponseCodeVNPAY;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private ResponseCodeVNPAY responseCodeVNPAY;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private Showtime showTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "translation_payment_id")
    private TranslationPayment translationPayment;
}
