package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.bill.Translation;
import com.nongviet201.cinema.core.repository.TranslationRepository;
import com.nongviet201.cinema.core.service.TranslationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TranslationServiceImpl implements TranslationService {

    private final TranslationRepository translationRepository;

    @Override
    public Translation findTranslationByBillId(Integer id) {
        return translationRepository.findByBillId(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin giao dịch với số hóa đơn là: " + id));
    }
}
