package com.bartek.shop.controller;

import com.bartek.shop.mapper.BasketMapper;
import com.bartek.shop.mapper.ProductMapper;
import com.bartek.shop.model.dto.BasketDto;
import com.bartek.shop.model.dto.ProductDto;
import com.bartek.shop.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/basket")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class BasketController {

    private final BasketService basketService;
    private final BasketMapper basketMapper;
    private final ProductMapper productMapper;

    @PostMapping
    public BasketDto saveBasket(@RequestBody BasketDto basketDto) {
        return basketMapper.mapDaoToDto(basketService.addToBasket(basketMapper.mapDtoToDao(basketDto), basketDto.getProduct_id()));
    }

    @PutMapping
    public BasketDto updateBasket(@RequestBody BasketDto basketDto) {
        return basketMapper.mapDaoToDto(basketService.updateProduct(basketMapper.mapDtoToDao(basketDto), basketDto.getProduct_id()));
    }

    @GetMapping
    public Set<ProductDto> getBasket() {
        return productMapper.mapDaoToDto(basketService.getUserBasket());
    }
}
