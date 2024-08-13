package com.nongviet201.cinema.web.sdk.controller.service;

import com.nongviet201.cinema.core.entity.bill.Bill;
import com.nongviet201.cinema.core.service.BillService;
import com.nongviet201.cinema.web.sdk.controller.decorator.WebUserBillProfileDecorator;
import com.nongviet201.cinema.web.sdk.response.WebUserBillProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WebUserBillProfileControllerService {

    private final BillService billService;
    private final WebUserBillProfileDecorator userBillProfileDecorator;

    public List<WebUserBillProfileResponse> getCurrentUserBillProfile() {
        List<Bill> bills = billService.getBillByUserId().stream().limit(20).toList();
        return bills.stream()
            .map(userBillProfileDecorator::decorate)
            .collect(Collectors.toList());
    }
}
