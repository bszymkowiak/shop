package com.bartek.shop.repository;

import com.bartek.shop.model.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }
