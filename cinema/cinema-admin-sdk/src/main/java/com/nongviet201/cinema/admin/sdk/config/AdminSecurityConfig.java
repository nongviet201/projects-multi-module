package com.nongviet201.cinema.admin.sdk.config;

import com.nongviet201.cinema.core.security.CustomFilter;
import com.nongviet201.cinema.core.security.CustomUserDetailService;
import com.nongviet201.cinema.core.security.SecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AdminSecurityConfig extends SecurityConfig{

    public AdminSecurityConfig(CustomUserDetailService userDetailsService, PasswordEncoder passwordEncoder, CustomFilter customFilter) {
        super(userDetailsService, passwordEncoder, customFilter);
    }

    @Bean
    public SecurityFilterChain adminFilterChain(
        HttpSecurity  http
    ) throws Exception {

        http.authorizeHttpRequests(auth -> {
                auth.requestMatchers("/admin/**").hasRole("ADMIN");
                auth.anyRequest().permitAll();
            });

        http.formLogin(login ->
            login.
                loginPage("/login")
                .permitAll()
        );

        http.logout(logout -> {
            logout.logoutSuccessUrl("/login");
        });

        return super.filterChain(http);
    }
}
