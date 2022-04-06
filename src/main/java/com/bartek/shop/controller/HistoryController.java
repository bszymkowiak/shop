package com.bartek.shop.controller;

import com.bartek.shop.model.dao.Product;
import com.bartek.shop.model.dao.User;
import com.bartek.shop.repository.ProductRepository;
import com.bartek.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.history.Revision;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/histories")
@RequiredArgsConstructor
public class HistoryController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @GetMapping("/{userId}")
    public Page<Revision<Long, User>> getUserHistory(@RequestParam int size, @RequestParam int page, @PathVariable Long userId) {
        return userRepository.findRevisions(userId, PageRequest.of(page, size));
    }

    @GetMapping("{productId}")
    public Page<Revision<Long, Product>> getProductHistory(@RequestParam int size, @RequestParam int page, @PathVariable Long productId) {
        return productRepository.findRevisions(productId, PageRequest.of(page, size));
    }
}
