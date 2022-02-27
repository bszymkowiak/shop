package com.bartek.shop.service;

import com.bartek.shop.model.dao.Product;
import com.bartek.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.getById(id);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> getPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional
    public Product updateProduct(Long id, Product product) {
        Product productdb = findById(id);

        productdb.setPrice(product.getPrice());
        productdb.setName(product.getName());
        productdb.setQuantity(product.getQuantity());

        return productdb;
    }
}
