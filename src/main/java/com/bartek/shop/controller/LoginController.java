package com.bartek.shop.controller;

import com.bartek.shop.model.dto.LoginDto;
import com.bartek.shop.model.dto.TokenDto;
import com.bartek.shop.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public TokenDto loginUser(@RequestBody LoginDto loginDto) {
        return loginService.loginUser(loginDto.getLogin(), loginDto.getPassword());
    }
}
