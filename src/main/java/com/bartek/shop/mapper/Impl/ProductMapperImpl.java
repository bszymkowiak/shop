//package com.bartek.shop.mapper.Impl;
//
//import com.bartek.shop.mapper.ProductMapper;
//import com.bartek.shop.model.dao.Product;
//import com.bartek.shop.model.dto.ProductDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ProductMapperImpl implements ProductMapper {
//    @Override
//    public ProductDto mapDaoToDto(Product product) {
//        return ProductDto.builder()
//                .id(product.getId())
//                .name(product.getName())
//                .price(product.getPrice())
//                .quantity(product.getQuantity())
//                .build();
//    }
//
//    @Override
//    public Product mapDToToDao(ProductDto productDto) {
//        return Product.builder()
//                .name(productDto.getName())
//                .price(productDto.getPrice())
//                .quantity(productDto.getQuantity())
//                .build();
//    }
//}
