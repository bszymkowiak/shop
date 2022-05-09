package com.bartek.shop.controller;

import com.bartek.shop.mapper.ProductMapper;
import com.bartek.shop.model.dto.ProductDto;
import com.bartek.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProductDto saveProduct(@RequestBody ProductDto product) {
        return productMapper.mapDaoToDto(productService.save(productMapper.mapDToToDao(product)));
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productMapper.mapDaoToDto(productService.findById(id));
    }

    @GetMapping
    public Page<ProductDto> getProducts(@RequestParam int page, @RequestParam int size) {
        return productService.getPage(PageRequest.of(page, size))
                .map(productMapper::mapDaoToDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productMapper.mapDaoToDto(productService.updateProduct(id, productMapper.mapDToToDao(productDto)));
    }
}
