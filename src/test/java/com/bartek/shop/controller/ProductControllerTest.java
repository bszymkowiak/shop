package com.bartek.shop.controller;

import com.bartek.shop.model.dao.Product;
import com.bartek.shop.model.dto.ProductDto;
import com.bartek.shop.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc // tworzy beana i pozwala wykonwac requesty na controllery
@ActiveProfiles("test") //profil w którym spring będzie uruchamiany to test
@TestPropertySource(locations = "classpath:application-test.yml")
@Transactional // testy musza byc niezalezne, dane ktore zapisalismy w jednym tescie nie powinny wplywac na testy w innej metodzie lub klasie, dlatego adnotacja rollbackuje nam wszystkie zmiany ktore wprowadzilismy na bazie danych po wykoananiu testu
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductRepository productRepository;

    @Test
    @WithMockUser(roles = "ADMIN")
    void saveProduct() throws Exception {

        MockMultipartFile image = new MockMultipartFile("image", "image.jpg", MediaType.IMAGE_JPEG_VALUE, new byte[0]);

        MockMultipartFile product = new MockMultipartFile("product", "", MediaType.APPLICATION_JSON_VALUE, objectMapper.writeValueAsBytes(ProductDto.builder()
                .name("product")
                .price(20.0)
                .quantity(1L)
                .build()));

        mockMvc.perform(multipart("/api/products")
                        .file(image)
                        .file(product)
                        .with(request -> {
                            request.setMethod("POST");
                            return request;
                        }))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("product"))
                .andExpect(jsonPath("$.price").value(20L))
                .andExpect(jsonPath("$.quantity").value(1L))
                .andExpect(jsonPath("$.filePath").value("target\\product.jpg"))
                .andExpect(jsonPath("$.revNumber").doesNotExist())
                .andExpect(jsonPath("$.revType").doesNotExist());
    }

    @Test
    void getProductById() {
    }

    @Test
    void getProducts() {
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteProduct() throws Exception {

        Product product = productRepository.save(Product.builder()
                .name("product")
                .quantity(20L)
                .price(20.0)
                .filePath("target\\product.jpg")
                .build());

        mockMvc.perform(delete("/api/products/" + product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void updateProduct() throws Exception {

        Product product = productRepository.save(Product.builder()
                .name("product")
                .quantity(20L)
                .price(20.0)
                .filePath("target\\product.jpg")
                .build());

        MockMultipartFile updatedProduct = new MockMultipartFile("product", "", MediaType.APPLICATION_JSON_VALUE, objectMapper.writeValueAsBytes(ProductDto.builder()
                .name("product2")
                .price(21.0)
                .quantity(2L)
                .build()));

        MockMultipartFile image = new MockMultipartFile("image", "image.jpg", MediaType.IMAGE_JPEG_VALUE, new byte[0]);

        mockMvc.perform(multipart("/api/products/" + product.getId())
                .file(updatedProduct)
                .file(image)
                .with(request -> {
                    request.setMethod("PUT");
                    return request;
                }))
                .andExpect(status().isOk());



    }
}