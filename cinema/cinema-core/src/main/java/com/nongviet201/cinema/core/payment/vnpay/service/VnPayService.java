package com.nongviet201.cinema.core.payment.vnpay.service;

import com.nongviet201.cinema.core.payment.vnpay.config.Config;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class VnPayService {

    public String createPayment(
        Integer billId,
        Long totalPrice,
        Integer timeRemain
    ) {
        Map<String, String> vnpParams = buildVnPayParams(billId, totalPrice, timeRemain);
        String queryUrl = generateQueryUrl(vnpParams);
        String secureHash = generateSecureHash(vnpParams);

        return Config.vnp_PayUrl + "?" + queryUrl + "&vnp_SecureHash=" + secureHash;
    }

    private String generateQueryUrl(
        Map<String, String> vnpParams
    ) {
        StringBuilder query = new StringBuilder();
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                if (query.length() > 0) {
                    query.append('&');
                }
                query.append(URLEncoder.encode(entry.getKey(), StandardCharsets.US_ASCII));
                query.append('=');
                query.append(URLEncoder.encode(entry.getValue(), StandardCharsets.US_ASCII));
            }
        }
        return query.toString();
    }

    private String generateSecureHash(
        Map<String, String> vnpParams
    ) {
        String hashData = vnpParams.entrySet().stream()
            .filter(entry -> entry.getValue() != null && !entry.getValue().isEmpty())
            .map(entry -> entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.US_ASCII))
            .collect(Collectors.joining("&"));
        return Config.hmacSHA512(Config.secretKey, hashData);
    }

    private Map<String, String> buildVnPayParams(
        Integer billId,
        Long totalPrice,
        Integer timeRemain
    ) {
        Map<String, String> vnpParams = new TreeMap<>();
        vnpParams.put("vnp_Version", Config.vnp_Version);
        vnpParams.put("vnp_Command", Config.vnp_Command);
        vnpParams.put("vnp_TmnCode", Config.vnp_TmnCode);
        vnpParams.put("vnp_Amount", String.valueOf(totalPrice * 100));
        vnpParams.put("vnp_CurrCode", "VND");
        vnpParams.put("vnp_TxnRef", String.valueOf(billId));
        vnpParams.put("vnp_OrderInfo", "Thanh toan don hang:" + billId);
        vnpParams.put("vnp_OrderType", Config.vnp_OrderType);
        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnpParams.put("vnp_IpAddr", "42.119.181.35");
        vnpParams.put("vnp_CreateDate", getCurrentDate());
        vnpParams.put("vnp_ExpireDate", getExpireDate(timeRemain));

        return vnpParams;
    }

    private String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        formatter.setTimeZone(TimeZone.getTimeZone("Etc/GMT+7"));
        return formatter.format(Calendar.getInstance().getTime());
    }

    private String getExpireDate(int timeRemain) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        calendar.add(Calendar.MINUTE, timeRemain);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(calendar.getTime());
    }
}
