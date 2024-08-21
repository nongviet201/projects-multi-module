package com.nongviet201.cinema.web.sdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
    basePackages = {
        "com.nongviet201.cinema.core.repository",
        "com.nongviet201.cinema.core.service",
        "com.nongviet201.cinema.web.sdk"
    })
public class CinemaWebSdkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaWebSdkApplication.class, args);
    }

}
