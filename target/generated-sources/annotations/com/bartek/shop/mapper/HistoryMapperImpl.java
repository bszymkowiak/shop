package com.bartek.shop.mapper;

import com.bartek.shop.model.dao.Category;
import com.bartek.shop.model.dao.Product;
import com.bartek.shop.model.dao.User;
import com.bartek.shop.model.dto.CategoryDto;
import com.bartek.shop.model.dto.CategoryDto.CategoryDtoBuilder;
import com.bartek.shop.model.dto.ProductDto;
import com.bartek.shop.model.dto.ProductDto.ProductDtoBuilder;
import com.bartek.shop.model.dto.UserDto;
import com.bartek.shop.model.dto.UserDto.UserDtoBuilder;
import javax.annotation.processing.Generated;
import org.springframework.data.history.Revision;
import org.springframework.data.history.RevisionMetadata;
import org.springframework.data.history.RevisionMetadata.RevisionType;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-03T13:10:47+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class HistoryMapperImpl implements HistoryMapper {

    @Override
    public UserDto mapUserRevisionToDto(Revision<Long, User> revision) {
        if ( revision == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.id( revisionEntityId( revision ) );
        userDto.firstName( revisionEntityFirstName( revision ) );
        userDto.lastName( revisionEntityLastName( revision ) );
        userDto.login( revisionEntityLogin( revision ) );
        userDto.email( revisionEntityEmail( revision ) );
        userDto.revNumber( revision.getRequiredRevisionNumber() );
        userDto.revType( revisionMetadataRevisionType( revision ) );

        return userDto.build();
    }

    @Override
    public ProductDto mapRevisionProductToDto(Revision<Long, Product> revision) {
        if ( revision == null ) {
            return null;
        }

        ProductDtoBuilder productDto = ProductDto.builder();

        productDto.id( revisionEntityId1( revision ) );
        productDto.name( revisionEntityName( revision ) );
        productDto.price( revisionEntityPrice( revision ) );
        productDto.quantity( revisionEntityQuantity( revision ) );
        productDto.revNumber( revision.getRequiredRevisionNumber() );
        productDto.revType( revisionMetadataRevisionType1( revision ) );

        return productDto.build();
    }

    @Override
    public CategoryDto mapRevisionCategoryToDto(Revision<Long, Category> revision) {
        if ( revision == null ) {
            return null;
        }

        CategoryDtoBuilder categoryDto = CategoryDto.builder();

        categoryDto.id( revisionEntityId2( revision ) );
        categoryDto.name( revisionEntityName1( revision ) );
        categoryDto.revNumber( revision.getRequiredRevisionNumber() );
        categoryDto.revType( revisionMetadataRevisionType2( revision ) );

        return categoryDto.build();
    }

    private Long revisionEntityId(Revision<Long, User> revision) {
        if ( revision == null ) {
            return null;
        }
        User entity = revision.getEntity();
        if ( entity == null ) {
            return null;
        }
        Long id = entity.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String revisionEntityFirstName(Revision<Long, User> revision) {
        if ( revision == null ) {
            return null;
        }
        User entity = revision.getEntity();
        if ( entity == null ) {
            return null;
        }
        String firstName = entity.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String revisionEntityLastName(Revision<Long, User> revision) {
        if ( revision == null ) {
            return null;
        }
        User entity = revision.getEntity();
        if ( entity == null ) {
            return null;
        }
        String lastName = entity.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private String revisionEntityLogin(Revision<Long, User> revision) {
        if ( revision == null ) {
            return null;
        }
        User entity = revision.getEntity();
        if ( entity == null ) {
            return null;
        }
        String login = entity.getLogin();
        if ( login == null ) {
            return null;
        }
        return login;
    }

    private String revisionEntityEmail(Revision<Long, User> revision) {
        if ( revision == null ) {
            return null;
        }
        User entity = revision.getEntity();
        if ( entity == null ) {
            return null;
        }
        String email = entity.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private RevisionType revisionMetadataRevisionType(Revision<Long, User> revision) {
        if ( revision == null ) {
            return null;
        }
        RevisionMetadata<Long> metadata = revision.getMetadata();
        if ( metadata == null ) {
            return null;
        }
        RevisionType revisionType = metadata.getRevisionType();
        if ( revisionType == null ) {
            return null;
        }
        return revisionType;
    }

    private Long revisionEntityId1(Revision<Long, Product> revision) {
        if ( revision == null ) {
            return null;
        }
        Product entity = revision.getEntity();
        if ( entity == null ) {
            return null;
        }
        Long id = entity.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String revisionEntityName(Revision<Long, Product> revision) {
        if ( revision == null ) {
            return null;
        }
        Product entity = revision.getEntity();
        if ( entity == null ) {
            return null;
        }
        String name = entity.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Double revisionEntityPrice(Revision<Long, Product> revision) {
        if ( revision == null ) {
            return null;
        }
        Product entity = revision.getEntity();
        if ( entity == null ) {
            return null;
        }
        Double price = entity.getPrice();
        if ( price == null ) {
            return null;
        }
        return price;
    }

    private Long revisionEntityQuantity(Revision<Long, Product> revision) {
        if ( revision == null ) {
            return null;
        }
        Product entity = revision.getEntity();
        if ( entity == null ) {
            return null;
        }
        Long quantity = entity.getQuantity();
        if ( quantity == null ) {
            return null;
        }
        return quantity;
    }

    private RevisionType revisionMetadataRevisionType1(Revision<Long, Product> revision) {
        if ( revision == null ) {
            return null;
        }
        RevisionMetadata<Long> metadata = revision.getMetadata();
        if ( metadata == null ) {
            return null;
        }
        RevisionType revisionType = metadata.getRevisionType();
        if ( revisionType == null ) {
            return null;
        }
        return revisionType;
    }

    private Long revisionEntityId2(Revision<Long, Category> revision) {
        if ( revision == null ) {
            return null;
        }
        Category entity = revision.getEntity();
        if ( entity == null ) {
            return null;
        }
        Long id = entity.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String revisionEntityName1(Revision<Long, Category> revision) {
        if ( revision == null ) {
            return null;
        }
        Category entity = revision.getEntity();
        if ( entity == null ) {
            return null;
        }
        String name = entity.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private RevisionType revisionMetadataRevisionType2(Revision<Long, Category> revision) {
        if ( revision == null ) {
            return null;
        }
        RevisionMetadata<Long> metadata = revision.getMetadata();
        if ( metadata == null ) {
            return null;
        }
        RevisionType revisionType = metadata.getRevisionType();
        if ( revisionType == null ) {
            return null;
        }
        return revisionType;
    }
}
