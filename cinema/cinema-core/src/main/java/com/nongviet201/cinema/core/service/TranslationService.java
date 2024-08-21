package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.bill.Translation;
import com.nongviet201.cinema.core.model.enums.bill.BillStatus;

import java.util.List;

public interface TranslationService {

    Translation findTranslationByBillId(Integer id);

    List<Translation> getAllTranslationByCinemaIdAndTimeAndBillStatus(int id, String time);
}
