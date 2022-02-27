package com.bartek.shop.controller;

import com.bartek.shop.mapper.UserMapper;
import com.bartek.shop.model.dto.UserDto;
import com.bartek.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserDto saveUser(@RequestBody @Valid UserDto user) {
        return userMapper.mapDaoToDto(userService.save(userMapper.mapDtoToDao(user)));
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userMapper.mapDaoToDto(userService.findById(id));
    }

    @GetMapping
    public Page<UserDto> getUsers(@RequestParam int page, @RequestParam int size) {
        return userService.getPage(PageRequest.of(page, size))
                .map(userMapper::mapDaoToDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody @Valid UserDto user) {
        return userMapper.mapDaoToDto(userService.updateUser(userMapper.mapDtoToDao(user), id));
    }
}