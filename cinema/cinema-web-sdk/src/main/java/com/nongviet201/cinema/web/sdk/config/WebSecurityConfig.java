package com.nongviet201.cinema.web.sdk.config;

import com.nongviet201.cinema.core.security.CustomFilter;
import com.nongviet201.cinema.core.security.CustomUserDetailService;
import com.nongviet201.cinema.core.security.SecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class WebSecurityConfig extends SecurityConfig{

    public WebSecurityConfig(CustomUserDetailService userDetailsService, PasswordEncoder passwordEncoder, CustomFilter customFilter) {
        super(userDetailsService, passwordEncoder, customFilter);
    }

    @Bean
    public SecurityFilterChain webFilterChain(
        HttpSecurity http
    ) throws Exception {

        String[] web = {
            "/booking/get/stage-two",
            "/booking/get/stage-three",
            "/user"
        };

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(web).authenticated();
            auth.anyRequest().permitAll();
        });

        return super.filterChain(http);
    }
}
