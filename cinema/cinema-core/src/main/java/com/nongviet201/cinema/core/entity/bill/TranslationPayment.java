package com.nongviet201.cinema.core.entity.bill;

import com.nongviet201.cinema.core.model.enums.bill.PaymentMethod;
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
    private String bankCode;
    private LocalDateTime payDate;
    private String transactionNo; //Mã giao dịch ghi nhận tại hệ thống VNPAY. Ví dụ: 20170829153052

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "response_codevnpay", length = 50)
    private ResponseCodeVNPAY responseCodeVNPAY;
}
