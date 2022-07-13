package com.bartek.shop.controller;

import com.bartek.shop.mapper.UserMapper;
import com.bartek.shop.model.dto.UserDto;
import com.bartek.shop.service.BasketService;
import com.bartek.shop.service.UserService;
import com.bartek.shop.validator.group.Create;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @Validated(Create.class)
    public UserDto saveUser(@RequestBody @Valid UserDto user) {
        return userMapper.mapDaoToDto(userService.save(userMapper.mapDtoToDao(user)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN') || @securityService.hasAccessToUser(#id))")
    public UserDto getUserById(@PathVariable Long id) {
        return userMapper.mapDaoToDto(userService.findById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserDto> getUsers(@RequestParam int page, @RequestParam int size) {
        return userService.getPage(PageRequest.of(page, size))
                .map(userMapper::mapDaoToDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN') || @ securityService.hasAccessToUser(#id))")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN') || @ securityService.hasAccessToUser(#id))")
    public UserDto updateUser(@PathVariable Long id, @RequestBody @Valid UserDto user) {
        return userMapper.mapDaoToDto(userService.updateUser(userMapper.mapDtoToDao(user), id));
    }
}