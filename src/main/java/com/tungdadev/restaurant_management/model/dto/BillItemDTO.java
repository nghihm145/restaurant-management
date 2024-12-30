package com.tungdadev.restaurant_management.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 09:58
 */
@Getter
@Setter
public class BillItemDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String menu;
    private Integer quantity;
    private String orderedTime;

    public BillItemDTO(String menu, Integer quantity, String orderedTime) {
        super();
        this.quantity = quantity;
        this.orderedTime = orderedTime;
    }
}
