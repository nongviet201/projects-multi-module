package com.nongviet201.cinema.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nongviet201.cinema"})
public class CinemaCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaCoreApplication.class, args);
    }

}
