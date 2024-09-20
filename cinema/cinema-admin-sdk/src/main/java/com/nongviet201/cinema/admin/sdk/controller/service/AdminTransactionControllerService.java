package com.nongviet201.cinema.admin.sdk.controller.service;

import com.nongviet201.cinema.admin.sdk.decorator.AdminBillDecorator;
import com.nongviet201.cinema.admin.sdk.decorator.AdminTransactionDecorator;
import com.nongviet201.cinema.admin.sdk.response.AdminBillResponse;
import com.nongviet201.cinema.core.entity.bill.BillCombo;
import com.nongviet201.cinema.core.entity.bill.BillSeat;
import com.nongviet201.cinema.core.entity.bill.Transaction;
import com.nongviet201.cinema.core.service.BillComboService;
import com.nongviet201.cinema.core.service.BillSeatService;
import com.nongviet201.cinema.core.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminTransactionControllerService {

    private final TransactionService transactionService;
    private final BillSeatService billSeatService;
    private final BillComboService billComboService;
    private final AdminBillDecorator decorator;

    public AdminBillResponse getBillById(
        Integer id
    ) {

        Transaction transaction = transactionService.getTransactionById(id);
        List<BillSeat> billSeat = billSeatService.getBillSeatByBillId(transaction.getBill().getId());
        List<BillCombo> billCombo = billComboService.getBillComboByBillId(transaction.getBill().getId());

        return decorator.decorate(
            transaction,
            billSeat,
            billCombo
        );
    }
}
