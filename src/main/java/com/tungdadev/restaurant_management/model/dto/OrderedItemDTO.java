package com.tungdadev.restaurant_management.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 09:59
 */
@Getter
@Setter
@NoArgsConstructor
public class OrderedItemDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private Integer quantity;

    public OrderedItemDTO(String name, Integer quantity) {
        super();
        this.name = name;
        this.quantity = quantity;
    }
}
