package com.bartek.shop.mapper;

import com.bartek.shop.model.dao.User;
import com.bartek.shop.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDto mapDaoToDto(User userDao);

    User mapDtoToDao(UserDto userDto);
}
