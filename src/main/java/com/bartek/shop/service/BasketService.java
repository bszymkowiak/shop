package com.bartek.shop.service;

import com.bartek.shop.model.dao.Basket;
import com.bartek.shop.model.dao.Product;
import com.bartek.shop.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;
    private final UserService userService;

    public Basket findById(Long id) {
        return basketRepository.getById(id);
    }

    public Basket addToBasket(Basket basket, Long id) {
        Product productDb = productService.findById(id);
        basket.setProduct(productDb);
        basket.setUser(userService.getCurrentUser());

        return basketRepository.save(basket);
    }

    @Transactional
    public Basket updateProduct(Basket basket, Long id) {
        Basket basketDb = basketRepository.getById(id);
        basketDb.setQuantity(basket.getQuantity());

        return basketDb;
    }

    public Set<Product> getUserBasket() {

        return basketRepository.findByUserId(userService.getCurrentUser().getId()).stream()
                .map(basket -> {
                    Product product = basket.getProduct();
                    product.setQuantity(basket.getQuantity());
                    return product;
                })
                .collect(Collectors.toSet());

    }
}