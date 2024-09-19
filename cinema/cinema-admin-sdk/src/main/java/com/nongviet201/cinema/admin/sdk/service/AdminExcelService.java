package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.response.AdminTransactionResponse;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminExcelService {

    public void exportTransactionToExcel(
        List<AdminTransactionResponse> records,
        Workbook workbook
    ) {
        Sheet sheet = workbook.createSheet("Transactions");


        String[] headers = {
            "Số Hóa Đơn",
            "Mã Giao Dịch",
            "Tên Khách Hàng",
            "SĐT",
            "Tên Rạp",
            "Mã Ngân Hàng",
            "Phương Thức Thanh Toán",
            "Ngày Giao Dịch",
            "Trạng Thái",
            "Tên Phim",
            "Thời Gian Chiếu",
            "Đã Trừ Khuyến Mãi",
            "Tổng Thanh Toán",
            "Điểm Tích Lũy Của Đơn Hàng"
        };

        // Tạo hàng đầu tiên cho tiêu đề
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        int rowNum = 1;
        for (AdminTransactionResponse record : records) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(record.getId());
            row.createCell(1).setCellValue(record.getTransactionNo());
            row.createCell(2).setCellValue(record.getUserName());
            row.createCell(3).setCellValue(record.getPhoneNumber());
            row.createCell(4).setCellValue(record.getCinemaName());
            row.createCell(5).setCellValue(record.getBankCode());
            row.createCell(6).setCellValue(record.getPaymentMethod().toString());
            row.createCell(7).setCellValue(record.getPayDate());
            row.createCell(8).setCellValue(record.getStatus());
            row.createCell(9).setCellValue(record.getMovieName());
            row.createCell(10).setCellValue(record.getScreeningDateTime());
            row.createCell(11).setCellValue(record.getDiscountAmount());
            row.createCell(12).setCellValue(record.getTotalPrice());
            row.createCell(13).setCellValue(record.getPoints());
        }

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
