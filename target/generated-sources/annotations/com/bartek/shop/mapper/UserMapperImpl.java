package com.bartek.shop.mapper;

import com.bartek.shop.model.dao.User;
import com.bartek.shop.model.dao.User.UserBuilder;
import com.bartek.shop.model.dto.UserDto;
import com.bartek.shop.model.dto.UserDto.UserDtoBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-29T08:37:18+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto mapDaoToDto(User userDao) {
        if ( userDao == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.id( userDao.getId() );
        userDto.firstName( userDao.getFirstName() );
        userDto.lastName( userDao.getLastName() );
        userDto.login( userDao.getLogin() );
        userDto.email( userDao.getEmail() );

        return userDto.build();
    }

    @Override
    public User mapDtoToDao(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.id( userDto.getId() );
        user.firstName( userDto.getFirstName() );
        user.lastName( userDto.getLastName() );
        user.login( userDto.getLogin() );
        user.password( userDto.getPassword() );
        user.email( userDto.getEmail() );

        return user.build();
    }
}
