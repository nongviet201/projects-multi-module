package com.nongviet201.cinema.web.sdk.controller.service;

import com.nongviet201.cinema.core.entity.bill.Bill;
import com.nongviet201.cinema.core.service.BillService;
import com.nongviet201.cinema.web.sdk.controller.decorator.WebFormatter;
import com.nongviet201.cinema.web.sdk.controller.decorator.WebUserBillProfileDecorator;
import com.nongviet201.cinema.web.sdk.response.WebBillDetailResponse;
import com.nongviet201.cinema.web.sdk.response.WebUserBillProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WebUserBillProfileControllerService {

    private final BillService billService;
    private final WebUserBillProfileDecorator userBillProfileDecorator;
    private final WebFormatter dateTimeFormatter;

    public Map<String, List<WebUserBillProfileResponse>> getCurrentUserBillProfile() {
        List<Bill> bills = billService.clientGetBillUserProfile().stream().limit(20).toList();

        return bills.stream()
            .collect(Collectors.groupingBy(
                bill -> dateTimeFormatter.formatDateTimeTommYYYY(bill.getPaymentAt()),
                LinkedHashMap::new,
                Collectors.mapping(
                    userBillProfileDecorator::decorate,
                    Collectors.toList()
                )
            ));
    }

    public WebBillDetailResponse getBillDetailById(
        Integer billId
    ) {
        return userBillProfileDecorator.billDetailDecorator(
            billService.clientGetBillDetailById(billId)
        );
    }
}
