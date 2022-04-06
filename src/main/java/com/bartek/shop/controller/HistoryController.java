package com.bartek.shop.controller;

import com.bartek.shop.mapper.HistoryMapper;
import com.bartek.shop.model.dto.CategoryDto;
import com.bartek.shop.model.dto.ProductDto;
import com.bartek.shop.model.dto.UserDto;
import com.bartek.shop.repository.CategoryRepository;
import com.bartek.shop.repository.ProductRepository;
import com.bartek.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/histories")
@RequiredArgsConstructor
public class HistoryController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final HistoryMapper historyMapper;

    @GetMapping("/users/{userId}")
    public Page<UserDto> getUserHistory(@RequestParam int size, @RequestParam int page, @PathVariable Long userId) {
        return userRepository.findRevisions(userId, PageRequest.of(page, size)).map(historyMapper::mapUserRevisionToDto);
    }

    @GetMapping("products/{productId}")
    public Page<ProductDto> getProductHistory(@RequestParam int size, @RequestParam int page, @PathVariable Long productId) {
        return productRepository.findRevisions(productId, PageRequest.of(page, size)).map(historyMapper::mapRevisionProductToDto);
    }

    @GetMapping("categories/{categoryId}")
    public Page<CategoryDto> getCategoryHistory(@RequestParam int size, @RequestParam int page, @PathVariable Long categoryId) {
        return categoryRepository.findRevisions(categoryId, PageRequest.of(page, size)).map(historyMapper::mapRevisionCategoryToDto);
    }
}
