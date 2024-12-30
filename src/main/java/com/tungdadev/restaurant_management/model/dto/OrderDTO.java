package com.tungdadev.restaurant_management.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 09:59
 */
@Getter
@Setter
@NoArgsConstructor
public class OrderDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private List<OrderedItemDTO> order;
}
