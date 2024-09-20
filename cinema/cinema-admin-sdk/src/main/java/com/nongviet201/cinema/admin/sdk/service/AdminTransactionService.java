package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.decorator.AdminTransactionDecorator;
import com.nongviet201.cinema.admin.sdk.request.UpsertTransactionRequest;
import com.nongviet201.cinema.admin.sdk.response.AdminTransactionResponse;
import com.nongviet201.cinema.core.service.TransactionService;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static com.nongviet201.cinema.core.utils.DateTimeUtils.parseDate;

@Service
@AllArgsConstructor
public class AdminTransactionService {

    private final TransactionService transactionService;
    private final AdminTransactionDecorator decorator;
    private final AdminExcelService adminExcelService;

    public List<AdminTransactionResponse> getTransactionFilter(
        UpsertTransactionRequest.GetDataFiller request
    ) {
        if (request.getToDate().isEmpty()) {
            return transactionService.filter(
                parseDate(request.getFormDate()).atStartOfDay(),
                parseDate(request.getFormDate()).atTime(LocalTime.MAX),
                request.getCinemaId()
            ).stream().map(decorator::decorate).toList();
        }

        return transactionService.filter(
            parseDate(request.getFormDate()).atStartOfDay(),
            parseDate(request.getToDate()).atTime(LocalTime.MAX),
            request.getCinemaId()
        ).stream().map(decorator::decorate).toList();
    }

    public AdminTransactionResponse getTransactionById(
        Integer id
    ) {
        return decorator.decorate(
            transactionService.getTransactionById(id)
        );
    }

    public byte[] getExcelData(
        UpsertTransactionRequest.GetDataFiller request
    ) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        adminExcelService.exportTransactionToExcel(
            getTransactionFilter(request),
            workbook
        );

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        workbook.close();

        return byteArrayOutputStream.toByteArray();
    }
}
