package com.bartek.shop.service;

import com.bartek.shop.model.dao.Category;
import com.bartek.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.getById(id);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
