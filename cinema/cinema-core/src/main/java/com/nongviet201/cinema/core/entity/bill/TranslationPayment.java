package com.nongviet201.cinema.core.entity.bill;

import com.nongviet201.cinema.core.model.enums.PaymentMethod;
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
@Table(name = "translation_payment")
public class TranslationPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private ResponseCodeVNPAY responseCodeVNPAY;
}
