package com.bartek.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean //zastosowaliśmy tutaj factory method
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
