package com.bartek.shop.model.dto;

import com.bartek.shop.model.dao.Product;
import com.bartek.shop.model.dao.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {

    @Null
    private Long id;
    private ProductDto product;
    private Long product_id;
    private Long quantity;
    private User user;
}
