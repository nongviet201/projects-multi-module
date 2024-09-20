package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.bill.Transaction;
import com.nongviet201.cinema.core.payment.vnpay.code.ResponseCodeVNPAY;
import com.nongviet201.cinema.core.repository.TransactionRepository;
import com.nongviet201.cinema.core.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction findTranslationByBillId(Integer id) {
        return transactionRepository.findByBillId(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin giao dịch với số hóa đơn là: " + id));
    }

    @Override
    public List<Transaction> getAllTranslationByCinemaIdAndTimeAndStatusCode(
        int id,
        String time
    ) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate;
        LocalDateTime endDate = switch (time) {
            case "day" -> {
                startDate = now.toLocalDate().atStartOfDay();
                yield now.toLocalDate().atTime(23, 59, 59);
            }
            case "week" -> {
                startDate = now.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)).toLocalDate().atStartOfDay();
                yield now.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY)).toLocalDate().atTime(23, 59, 59);
            }
            case "month" -> {
                startDate = now.withDayOfMonth(1).toLocalDate().atStartOfDay();
                yield now.with(TemporalAdjusters.lastDayOfMonth()).toLocalDate().atTime(23, 59, 59);
            }
            case "year" -> {
                startDate = now.withDayOfYear(1).toLocalDate().atStartOfDay();
                yield now.with(TemporalAdjusters.lastDayOfYear()).toLocalDate().atTime(23, 59, 59);
            }
            default -> throw new IllegalArgumentException("Thời gian cung cấp không khả dụng: " + time);
        };

        return transactionRepository.findAllByCinema_IdAndPayDateBetweenAndStatusAndResponseCodeVNPAY(
            id,
            startDate,
            endDate,
            true,
            ResponseCodeVNPAY.PAYMENT_SUCCESS
        );
    }

    @Override
    public List<Transaction> getAllTranslationByTimeAndStatusAndCode(
        LocalDateTime startDate,
        LocalDateTime endDate,
        boolean status,
        ResponseCodeVNPAY responseCodeVNPAY
    ) {
        return transactionRepository.findAllByPayDateBetweenAndStatusAndResponseCodeVNPAY(
            startDate,
            endDate,
            status,
            responseCodeVNPAY
        );
    }

    @Override
    public List<Transaction> filter(
        LocalDateTime formDate,
        LocalDateTime toDate,
        Integer cinemaId
    ) {
        if (cinemaId != null) {
            return transactionRepository.findAllByPayDateBetweenAndCinema_Id(
                formDate,
                toDate,
                cinemaId
            );
        }
        return transactionRepository.findAllByPayDateBetween(
            formDate,
            toDate
        );
    }

    @Override
    public Transaction getTransactionById(Integer id) {
        return transactionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin giao dịch với ID: " + id));
    }
}
