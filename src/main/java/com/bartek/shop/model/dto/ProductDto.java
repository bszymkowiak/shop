package com.bartek.shop.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.history.RevisionMetadata;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    private Long id;
    private String name;
    private Double price;
    private Long quantity;
    private Long revNumber;
    private RevisionMetadata.RevisionType revType;
}
