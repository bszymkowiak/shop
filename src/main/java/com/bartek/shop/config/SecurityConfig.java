package com.bartek.shop.config;

import com.bartek.shop.security.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity // jest już w niej zawarta @Configuration - więc nie musimy jej dodawać
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) // włączenie adnotacji pre i post authorised
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.addFilter(new JwtAuthorizationFilter(authenticationManager())) //add filter rejestruje naszą autoryzację w spring Security
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Dwie metody, które konfigurują politykę zarządzania sesją w aplikacji
        // stateless oznacza ze po wykoanniu requestu serwer zapomina o uzytkowniku ktory wykonywal dany request
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}