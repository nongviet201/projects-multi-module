package com.nongviet201.cinema.core.service.impl;


import com.nongviet201.cinema.core.service.BarCodeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
@AllArgsConstructor
public class BarCodeServiceImpl implements BarCodeService {

    @Override
    public String generateBarCode(
        Integer billId
    ) {
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        try {
//            BitMatrix bitMatrix = qrCodeWriter.encode(String.valueOf(billId), BarcodeFormat.CODE_128, 200, 100);
//            Path path = FileSystems.getDefault().getPath("images/barcode/QRCode.png");
//            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
//            System.out.println("QR Code generated successfully!");
//            return "images/barcode/QRCode.png";
//        } catch (WriterException | IOException e) {
//            e.printStackTrace();
//        }
        return "";
    }
}
