package com.bartek.shop.service;

import com.bartek.shop.model.dao.User;
import com.bartek.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
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

}
