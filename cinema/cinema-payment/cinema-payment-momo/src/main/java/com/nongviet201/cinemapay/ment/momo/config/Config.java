package com.nongviet201.cinemapay.ment.momo.config;

import org.springframework.context.annotation.Configuration;

import javax.crypto.Mac;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Date;

@Configuration
public class Config {

    // Thay đổi giá trị biến tương ứng với dữ liệu của bạn
    String accessKey = "F8BBA842ECF85";
    String secretKey = "K951B6PE1waDMi640xX08PD3vg6EkVlz";
    String orderInfo = "pay with MoMo";
    String partnerCode = "MOMO";
    String redirectUrl = "https://webhook.site/b3088a6a-2d17-4f8d-a383-71389a6c600b";
    String ipnUrl = "https://webhook.site/b3088a6a-2d17-4f8d-a383-71389a6c600b";
    String requestType = "payWithMethod";
    String amount = "50000";

}