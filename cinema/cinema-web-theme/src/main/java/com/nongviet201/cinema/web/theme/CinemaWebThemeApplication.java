package com.nongviet201.cinema.web.theme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nongviet201.cinema.core"})
public class CinemaWebThemeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaWebThemeApplication.class, args);
    }

}
