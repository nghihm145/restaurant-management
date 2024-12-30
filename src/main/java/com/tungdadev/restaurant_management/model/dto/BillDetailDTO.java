package com.tungdadev.restaurant_management.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 09:58
 */
@Getter
@Setter
@NoArgsConstructor
public class BillDetailDTO implements Serializable {

    private Long billId;
    private String menuItem;
    private int quantity;
    private Date orderedTime;
    private double subTotal;
}
