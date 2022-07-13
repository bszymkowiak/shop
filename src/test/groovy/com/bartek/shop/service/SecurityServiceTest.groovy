package com.bartek.shop.service

import com.bartek.shop.model.dao.User
import spock.lang.Specification

class SecurityServiceTest extends Specification {

    def userService = Mock(UserService)
    def securityService = new SecurityService(userService)

    def "HasAccessToUser"() {
        given:
        def user = Mock(User)

        when:
        def result = securityService.hasAccessToUser(userId)

        then:
        1 * userService.getCurrentUser() >> user
        1 * user.getId() >> 1
        0 * _

        result == expected

        where:
        userId || expected
        1      || true
        2      || false
    }
}
