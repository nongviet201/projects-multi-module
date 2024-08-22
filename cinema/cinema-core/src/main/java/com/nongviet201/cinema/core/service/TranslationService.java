package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.bill.Translation;
import com.nongviet201.cinema.core.payment.vnpay.code.ResponseCodeVNPAY;

import java.time.LocalDateTime;
import java.util.List;

public interface TranslationService {

    Translation findTranslationByBillId(Integer id);

    List<Translation> getAllTranslationByCinemaIdAndTimeAndStatusCode(int id, String time);

    List<Translation> getAllTranslationByTimeAndStatusAndCode(LocalDateTime startDate, LocalDateTime endDate, boolean b, ResponseCodeVNPAY responseCodeVNPAY);
}
