package com.bartek.shop.service;

import com.bartek.shop.model.dao.Category;
import com.bartek.shop.model.dto.CategoryDto;
import com.bartek.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    public Page<Category> getPage(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Transactional
    public Category updateCategory(Long id, Category category) {
        Category categoryDb = getCategoryById(id);
        categoryDb.setName(category.getName());

        return categoryDb;
    }
}
