package com.bartek.shop.mapper;

import com.bartek.shop.model.dao.Product;
import com.bartek.shop.model.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto mapDaoToDto(Product product);

    Product mapDToToDao(ProductDto productDto);

    Set<ProductDto> mapDaoToDto(Set<Product> products);


}
