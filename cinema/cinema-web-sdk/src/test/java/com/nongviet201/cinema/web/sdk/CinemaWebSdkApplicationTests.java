package com.nongviet201.cinema.web.sdk;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(
    basePackages = {
        "com.nongviet201.cinema.core.repository",
        "com.nongviet201.cinema.core.service",
        "com.nongviet201.cinema.web.sdk"
    })
class CinemaWebSdkApplicationTests {

    @Test
    void contextLoads() {
    }

}
