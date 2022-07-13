package com.bartek.shop.service

import com.bartek.shop.model.dao.Role
import com.bartek.shop.model.dao.User
import com.bartek.shop.repository.RoleRepository
import com.bartek.shop.repository.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

import javax.persistence.EntityNotFoundException

class UserServiceSpec extends Specification {

    def userRepository = Mock(UserRepository)
    def passwordEncoder = Mock(PasswordEncoder)
    def roleRepository = Mock(RoleRepository)
    def userService = new UserService(userRepository, passwordEncoder, roleRepository)

    def "Save"() {
        given:
        def user = Mock(User)

        when:
        userService.save(user)

        then:
        1 * user.getPassword() >> "password"
        1 * passwordEncoder.encode("password") >> "encodedPassword"
        1 * user.setPassword("encodedPassword")
        1 * roleRepository.findByName("ROLE_USER") >> Optional.of(new Role())
        1 * user.setRoles(Collections.singleton(new Role()))
        1 * userRepository.save(user)
    }

    def "FindById"() {
        given:
        def id = 1

        when:
        userService.findById(id)

        then:
        1 * userRepository.getById(id)
        0 * _
    }

    def "DeleteById"() {
        given:
        def id = 1

        when:
        userService.deleteById(id)

        then:
        1 * userRepository.deleteById(id)
        0 * _
    }

    def "GetPage"() {
        given:
        def pageable = PageRequest.of(1, 5)

        when:
        userService.getPage(pageable)

        then:
        1 * userRepository.findAll(pageable)
        0 * _
    }

    def "UpdateUser"() {
        given:
        def user = Mock(User)
        def id = 1
        def userDb = Mock(User)

        when:
        userService.updateUser(user, id)

        then:
        1 * userRepository.getById(id) >> userDb
        1 * user.getEmail() >> "user@email.com"
        1 * userDb.setEmail("user@email.com")
        1 * user.getFirstName() >> "firstName"
        1 * userDb.setFirstName("firstName")
        1 * user.getLastName() >> "lastName"
        1 * userDb.setLastName("lastName")
        0 * _
    }

    def "GetCurrentUser"() {

        given:
        def securityContext = Mock(SecurityContext)
        SecurityContextHolder.setContext(securityContext)
        def authentication = Mock(Authentication)
        def user = Optional.of(new User())


        when:
        userService.getCurrentUser()

        then:
        1 * securityContext.getAuthentication() >> authentication
        1 * authentication.getName() >> "auth@email.com"
        1 * userRepository.findByEmail("auth@email.com") >> user
        0 * _

    }

    def "GetCurrentUser throw exception"() {
        given:
        def securityContext = Mock(SecurityContext)
        SecurityContextHolder.setContext(securityContext)
        def authentication = Mock(Authentication)
        def emptyOptional = Optional.empty()


        when:
        userService.getCurrentUser()

        then:
        1 * securityContext.getAuthentication() >> authentication
        1 * authentication.getName() >> "auth@email.com"
        1 * userRepository.findByEmail("auth@email.com") >> emptyOptional
        0 * _

        thrown EntityNotFoundException
    }
}
