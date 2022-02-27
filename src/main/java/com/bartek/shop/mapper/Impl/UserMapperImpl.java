//package com.bartek.shop.mapper.Impl;
//
//import com.bartek.shop.mapper.UserMapper;
//import com.bartek.shop.model.dao.User;
//import com.bartek.shop.model.dto.UserDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserMapperImpl implements UserMapper {
//
//    @Override
//    public UserDto mapDaoToDto(User userDao) {
//        return UserDto.builder()
//                .id(userDao.getId())
//                .email(userDao.getEmail())
//                .firstName(userDao.getFirstName())
//                .lastName(userDao.getLastName())
//                .login(userDao.getLogin())
//                .build();
//    }
//
//    @Override
//    public User mapDtoToDao(UserDto userDto) {
//        return User.builder()
//                .email(userDto.getEmail())
//                .firstName(userDto.getFirstName())
//                .lastName(userDto.getLastName())
//                .login(userDto.getLogin())
//                .password(userDto.getPassword())
//                .build();
//    }
//}
