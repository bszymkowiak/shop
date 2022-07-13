package com.bartek.shop.repository;

import com.bartek.shop.model.dao.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BasketRepository extends JpaRepository <Basket, Long> {

    Set<Basket> findByUserId(Long id);
}
