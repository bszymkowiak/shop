package com.bartek.shop.mapper;

import com.bartek.shop.model.dao.Basket;
import com.bartek.shop.model.dto.BasketDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasketMapper {

    Basket mapDtoToDao(BasketDto basketDto);
    BasketDto mapDaoToDto(Basket basket);
}
