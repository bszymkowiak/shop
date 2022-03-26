package com.bartek.shop.repository;

import com.bartek.shop.model.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface UserRepository extends JpaRepository<User, Long>, RevisionRepository<User, Long, Long> {
}
