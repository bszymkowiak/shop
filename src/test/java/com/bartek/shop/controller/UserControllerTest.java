package com.bartek.shop.controller;

import com.bartek.shop.model.dao.User;
import com.bartek.shop.model.dto.UserDto;
import com.bartek.shop.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc // tworzy beana i pozwala wykonwac requesty na controllery
@ActiveProfiles("test") //profil w którym spring będzie uruchamiany to test
@TestPropertySource(locations = "classpath:application-test.yml")
@Transactional // testy musza byc niezalezne, dane ktore zapisalismy w jednym tescie nie powinny wplywac na testy w innej metodzie lub klasie, dlatego adnotacja rollbackuje nam wszystkie zmiany ktore wprowadzilismy na bazie danych po wykoananiu testu
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveUser() throws Exception {

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserDto.builder()
                                .firstName("firstName")
                                .lastName("lastName")
                                .login("login")
                                .password("password")
                                .confirmedPassword("password")
                                .email("email@email.com")
                                .build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("firstName"))
                .andExpect(jsonPath("$.lastName").value("lastName"))
                .andExpect(jsonPath("$.login").value("login"))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.confirmedPassword").doesNotExist())
                .andExpect(jsonPath("$.email").value("email@email.com"))
                .andExpect(jsonPath("$.revNumber").doesNotExist())
                .andExpect(jsonPath("$.revType").doesNotExist())
        ;
    }

    @Test
    void shouldNotSaveUserWithInvalidInput() throws Exception {

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserDto.builder()
                                .firstName(null)
                                .lastName(" ")
                                .login("")
                                .password("password2")
                                .confirmedPassword("password2")
                                .email("email.email.com")
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[*].field", containsInAnyOrder("firstName", "lastName", "login", "email")))
                .andExpect(jsonPath("$[*].message", containsInAnyOrder("Nie możesz podać żadnej wartości",
                        "must not be blank", "must be a well-formed email address", "must not be blank")))
        ;
    }

    @Test
    void shouldNotSaveUserWithNotUniqueEmail() throws Exception {

        userRepository.save(User.builder()
                .firstName("fistName")
                .lastName("lastName")
                .login("login")
                .password("password")
                .email("email@email.com")
                .build());


        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserDto.builder()
                                .firstName("firstName")
                                .lastName("lastName")
                                .login("login")
                                .password("password")
                                .confirmedPassword("password")
                                .email("email@email.com")
                                .build())))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Data already exists."))
        ;

    }

    @Test
    @WithMockUser(username = "email@email.com")
    void getUserById() throws Exception {

        User user = userRepository.save(User.builder()
                .firstName("firstName")
                .lastName("lastName")
                .login("login")
                .password("password")
                .email("email@email.com")
                .build());

        mockMvc.perform(get("/api/users/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("firstName"))
                .andExpect(jsonPath("$.lastName").value("lastName"))
                .andExpect(jsonPath("$.login").value("login"))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.confirmedPassword").doesNotExist())
                .andExpect(jsonPath("$.email").value("email@email.com"))
                .andExpect(jsonPath("$.revNumber").doesNotExist())
                .andExpect(jsonPath("$.revType").doesNotExist());

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getUsers() throws Exception {

        userRepository.save(User.builder()
                .firstName("firstName")
                .lastName("lastName")
                .login("login")
                .password("password")
                .email("email@email.com")
                .build());

        userRepository.save(User.builder()
                .firstName("firstName2")
                .lastName("lastName2")
                .login("login2")
                .password("password")
                .email("email2@email.com")
                .build());


        mockMvc.perform(get("/api/users")
                        .queryParam("page", "0")
                        .queryParam("size", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
        ;

    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}