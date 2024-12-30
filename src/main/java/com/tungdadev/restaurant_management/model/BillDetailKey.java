package com.tungdadev.restaurant_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 09:54
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class BillDetailKey implements Serializable {
    @Column(name = "menu_item_id")
    Long menuItemId;

    @Column(name = "bill_id")
    Long billId;

    public BillDetailKey(Long billId, Long menuItemId) {
        super();
        this.menuItemId = menuItemId;
        this.billId = billId;
    }
}
