package org.ashina.ecommerce.order.application.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddProductToCartDto {

    @NotBlank
    private String productId;

    @NotBlank
    private Integer quantity;
}