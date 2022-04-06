package com.bartek.shop.mapper;

import com.bartek.shop.model.dao.Category;
import com.bartek.shop.model.dao.Product;
import com.bartek.shop.model.dao.User;
import com.bartek.shop.model.dto.CategoryDto;
import com.bartek.shop.model.dto.ProductDto;
import com.bartek.shop.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.history.Revision;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "entity.firstName", target = "firstName")
    @Mapping(source = "entity.lastName", target = "lastName")
    @Mapping(source = "entity.login", target = "login")
    @Mapping(source = "entity.email", target = "email")
    @Mapping(source = "requiredRevisionNumber", target = "revNumber")
    @Mapping(source = "metadata.revisionType", target = "revType")
    UserDto mapUserRevisionToDto(Revision<Long, User> revision);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "entity.name", target = "name")
    @Mapping(source = "entity.price", target = "price")
    @Mapping(source = "entity.quantity", target = "quantity")
    @Mapping(source = "requiredRevisionNumber", target = "revNumber")
    @Mapping(source = "metadata.revisionType", target = "revType")
    ProductDto mapRevisionProductToDto(Revision<Long, Product> revision);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "entity.name", target = "name")
    @Mapping(source = "requiredRevisionNumber", target = "revNumber")
    @Mapping(source = "metadata.revisionType", target = "revType")
    CategoryDto mapRevisionCategoryToDto(Revision<Long, Category> revision);

}
