package com.nongviet201.cinema.web.sdk.controller.service;

import com.nongviet201.cinema.core.service.BillService;
import com.nongviet201.cinema.core.service.TranslationService;
import com.nongviet201.cinema.web.sdk.controller.decorator.WebBillDecorator;
import com.nongviet201.cinema.web.sdk.response.WebBillResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebBillControllerService {
    private final BillService billService;
    private final WebBillDecorator billDecorator;
    private final TranslationService translationService;

    public WebBillResponse getBillById(Integer id) {
        return billDecorator.decorate(
            translationService.findTranslationByBillId(id)
        );
    }
}
