package com.bartek.shop.service;

import com.bartek.shop.model.dao.Product;
import com.bartek.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    @CachePut(cacheNames = "product", key = "#result.id")
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Cacheable(cacheNames = "product", key = "#id")
    public Product findById(Long id) {
        log.info("Getting product from DB by id {}", id);
        return productRepository.getById(id);
    }

    @CacheEvict(cacheNames = "product", key = "#id")
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> getPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional
    @CachePut(cacheNames = "product", key = "#id")
    public Product updateProduct(Long id, Product product) {
        Product productdb = findById(id);

        productdb.setPrice(product.getPrice());
        productdb.setName(product.getName());
        productdb.setQuantity(product.getQuantity());

        return productdb;
    }
}