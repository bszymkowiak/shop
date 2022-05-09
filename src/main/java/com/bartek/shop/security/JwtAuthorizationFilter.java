package com.bartek.shop.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null || !token.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC512("sekretnyklucz"))
                    .build()
                    .verify(token.replace("Bearer ", ""));

            String email = jwt.getSubject();
            String authorities = jwt.getClaim("authorities").as(String.class);
            List<SimpleGrantedAuthority> listOfAuthorities = new ArrayList<>();
            if (authorities != null && !authorities.isEmpty()) {
                listOfAuthorities = Arrays.stream(authorities.split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
            }
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, null, listOfAuthorities);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            chain.doFilter(request, response);
        } catch (JWTVerificationException exception) {
            response.setStatus(403);
        }
    }
    //1. pobieramy token z HeaderRequestu
    //2. Sprawdzamy czy token nie jest poprawny
    //3. Jeśli jest niepoprawny to przechodzimy dalej w łańcuchu wywołań
    //4. Jeśli jest poprawny to rozparsowujemy go i weryfikujemy sumę kontrolną (algorytm gdy przeczyta mi [jak w peselu]) używając sekretnego klucza
    //5. pobieramy z rozparsowanego tokena email i jego role użytkownika
    //6. Mapujemy role ze Stringa na List<SimpleGrantedAuthority>
    //7. Tworzymy obiekt UsernamePasswordAuthenticationToken
    //8. Wkładamy do kontekstu security
}
