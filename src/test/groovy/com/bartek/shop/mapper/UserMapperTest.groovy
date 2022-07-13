package com.bartek.shop.mapper


import com.bartek.shop.model.dao.User
import com.bartek.shop.model.dto.UserDto
import spock.lang.Specification

class UserMapperTest extends Specification {

    def userMapper = new UserMapperImpl()

    def "MapDaoToDto"() {
        given:
        def user = User.builder()
        .id(1)
        .firstName("firstName")
        .lastName("lastName")
        .login("login")
        .password("password")
        .email("email@email.com")
        .build()

        when:
        def result = userMapper.mapDaoToDto(user)

        then:
        result.id == 1
        result.firstName == "firstName"
        result.lastName == "lastName"
        result.login == "login"
        result.password == null
        result.confirmedPassword == null
        result.email == "email@email.com"
        result.revNumber == null
        result.revType == null

    }

    def "MapDtoToDao"() {
        given:
        def userDto = Mock(UserDto)

        when:
        userMapper.mapDtoToDao(userDto)

        then:
        1 * userMapper.mapDtoToDao(userDto)
    }
}
