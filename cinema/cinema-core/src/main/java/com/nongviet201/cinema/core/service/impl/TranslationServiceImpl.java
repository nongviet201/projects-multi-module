package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.bill.Translation;
import com.nongviet201.cinema.core.model.enums.bill.BillStatus;
import com.nongviet201.cinema.core.payment.vnpay.code.ResponseCodeVNPAY;
import com.nongviet201.cinema.core.repository.TranslationRepository;
import com.nongviet201.cinema.core.service.TranslationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@AllArgsConstructor
public class TranslationServiceImpl implements TranslationService {

    private final TranslationRepository translationRepository;

    @Override
    public Translation findTranslationByBillId(Integer id) {
        return translationRepository.findByBillId(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin giao dịch với số hóa đơn là: " + id));
    }

    @Override
    public List<Translation> getAllTranslationByCinemaIdAndTimeAndBillStatus(
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

        return translationRepository.findAllByCinema_IdAndPayDateBetweenAndStatusAndResponseCodeVNPAY(
            id,
            startDate,
            endDate,
            true,
            ResponseCodeVNPAY.PAYMENT_SUCCESS
        );
    }
}
