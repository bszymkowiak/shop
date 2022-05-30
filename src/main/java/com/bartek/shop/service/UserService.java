package com.bartek.shop.service;

import com.bartek.shop.model.dao.User;
import com.bartek.shop.repository.RoleRepository;
import com.bartek.shop.repository.UserRepository;
import com.bartek.shop.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        roleRepository.findByName("ROLE_USER").ifPresent(role -> user.setRoles(Collections.singleton(role))); //metoda singleton tworzy nam seta z jednym obiektem
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.getById(id);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional
    public User updateUser(User user, Long id) {
        User userDb = findById(id);

        userDb.setEmail(user.getEmail());
        userDb.setFirstName(user.getFirstName());
        userDb.setLastName(user.getLastName());

        return userDb;
    }

    public User getCurrentUser() {
        return userRepository.findByEmail(SecurityUtils.getCurrentUserName())
                .orElseThrow(EntityNotFoundException::new);
    }
}
