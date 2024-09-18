package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.bill.Transaction;
import com.nongviet201.cinema.core.payment.vnpay.code.ResponseCodeVNPAY;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TranslationService {

    Transaction findTranslationByBillId(Integer id);

    List<Transaction> getAllTranslationByCinemaIdAndTimeAndStatusCode(int id, String time);

    List<Transaction> getAllTranslationByTimeAndStatusAndCode(LocalDateTime startDate, LocalDateTime endDate, boolean b, ResponseCodeVNPAY responseCodeVNPAY);

    List<Transaction> filter(LocalDateTime formDate, LocalDateTime toDate, Integer cinemaId);

    Transaction getTransactionById(Integer id);
}
