package com.nongviet201.cinema.admin.theme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nongviet201.cinema.core"})
public class CinemaAdminThemeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaAdminThemeApplication.class, args);
    }

}
