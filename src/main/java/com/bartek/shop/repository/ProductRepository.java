package com.bartek.shop.repository;

import com.bartek.shop.model.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, RevisionRepository<Product, Long, Long > { }
