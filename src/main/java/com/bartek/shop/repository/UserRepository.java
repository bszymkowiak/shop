package com.bartek.shop.repository;

import com.bartek.shop.model.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, RevisionRepository<User, Long, Long> {

    Optional<User> findByEmail(String email);
}
