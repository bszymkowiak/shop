//package com.bartek.shop.mapper.Impl;
//
//import com.bartek.shop.mapper.CategoryMapper;
//import com.bartek.shop.model.dao.Category;
//import com.bartek.shop.model.dto.CategoryDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CategoryMapperImpl implements CategoryMapper {
//
//    @Override
//    public CategoryDto mapDaoToDto(Category product) {
//        return CategoryDto.builder()
//                .id(product.getId())
//                .name(product.getName())
//                .build();
//    }
//
//    @Override
//    public Category mapDtoToDao(CategoryDto productDto) {
//        return Category.builder()
//                .name(productDto.getName())
//                .build();
//    }
//}
