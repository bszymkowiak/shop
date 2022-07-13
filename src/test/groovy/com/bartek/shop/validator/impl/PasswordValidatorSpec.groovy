package com.bartek.shop.validator.impl

import com.bartek.shop.model.dto.UserDto
import spock.lang.Specification

class PasswordValidatorSpec extends Specification {

    def passValid = new PasswordValidator()

    def "Should test password validator"() {
        given:
        def user = new UserDto(password: password, confirmedPassword: confirmedPassword)

        when:
        def result = passValid.isValid(user, null)

        then:
        result == excepected

        where:
        password   | confirmedPassword || excepected
        "password" | "password"        || true
        "password" | "badPassword"     || false
    }
}
