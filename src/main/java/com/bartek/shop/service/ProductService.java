package com.bartek.shop.service;

import com.bartek.shop.config.properties.FilePathConfig;
import com.bartek.shop.helper.FileHelper;
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
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final FilePathConfig filePathConfig;
    private final FileHelper fileHelper;

    @CachePut(cacheNames = "product", key = "#result.id")
    public Product save(Product product, MultipartFile image) {

        try {
            Path path = Paths.get(filePathConfig.getProduct(), product.getName() + "." + FilenameUtils.getExtension(image.getOriginalFilename()));
            fileHelper.saveFile(image.getInputStream(), path);
            product.setFilePath(path.toString());
        } catch (IOException e) {
            log.warn("Could not save file", e);
        }

        return productRepository.save(product);
    }

    @Cacheable(cacheNames = "product", key = "#id")
    public Product findById(Long id) {
        log.info("Getting product from DB by id {}", id);
        return productRepository.getById(id);
    }

    @CacheEvict(cacheNames = "product", key = "#id")
    public void deleteProductById(Long id) {
        Path pathDb = Path.of(productRepository.getById(id).getFilePath());
        try {
            fileHelper.deleteFile(pathDb);
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
        productRepository.deleteById(id);
    }

    public Page<Product> getPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional
    @CachePut(cacheNames = "product", key = "#id")
    public Product updateProduct(Long id, Product product, MultipartFile image) {
        Product productdb = findById(id);
        String productdbFilePath = productdb.getFilePath();

        productdb.setPrice(product.getPrice());
        productdb.setName(product.getName());
        productdb.setQuantity(product.getQuantity());

        if (image != null && !image.isEmpty()) {
            try {
                Path path = Paths.get(filePathConfig.getProduct(), productdb.getName() + "." + FilenameUtils.getExtension(image.getOriginalFilename()));
                fileHelper.saveFile(image.getInputStream(), path);
                product.setFilePath(path.toString());
                if (!productdb.getFilePath().equals(productdbFilePath)) {
                    fileHelper.deleteFile(Paths.get(productdbFilePath));
                }
            } catch (IOException e) {
                log.warn("Could not save file", e);
            }
        }

        return productdb;
    }
}