package com.bartek.shop.mapper;

import com.bartek.shop.model.dao.Category;
import com.bartek.shop.model.dto.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto mapDaoToDto(Category product);

    Category mapDtoToDao(CategoryDto productDto);
}