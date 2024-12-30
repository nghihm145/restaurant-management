package com.tungdadev.restaurant_management.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 09:57
 */
@Getter
@Setter
@NoArgsConstructor
public class BillDTO implements Serializable {

    private Long id;
    Set<BillDetailDTO> billDetails = new HashSet<>();
    private Double total;
}
