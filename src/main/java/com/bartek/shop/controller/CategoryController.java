package com.bartek.shop.controller;

import com.bartek.shop.mapper.CategoryMapper;
import com.bartek.shop.model.dto.CategoryDto;
import com.bartek.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryMapper.mapDaoToDto(categoryService.getCategoryById(id));
    }

    @GetMapping
    public Page<CategoryDto> getCategories(@RequestParam int page, @RequestParam int size) {
        return categoryService.getPage(PageRequest.of(page, size))
                .map(categoryMapper::mapDaoToDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDto saveCategory(@RequestBody CategoryDto categoryDto) {
        return categoryMapper.mapDaoToDto(categoryService.save(categoryMapper.mapDtoToDao(categoryDto)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDto updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return categoryMapper.mapDaoToDto(categoryService.updateCategory(id, categoryMapper.mapDtoToDao(categoryDto)));
    }
}
