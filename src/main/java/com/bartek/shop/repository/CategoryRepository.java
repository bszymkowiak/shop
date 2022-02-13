package com.bartek.shop.repository;

import com.bartek.shop.model.dao.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
