package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.bill.Translation;

public interface TranslationService {

    Translation findTranslationByBillId(Integer id);
}
