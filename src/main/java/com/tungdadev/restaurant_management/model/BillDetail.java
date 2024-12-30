package com.tungdadev.restaurant_management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 09:53
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bill_menu_item")
public class BillDetail {
    @EmbeddedId
    private BillDetailKey id;

    @ManyToOne
    @MapsId("menu_item_id")
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    @ManyToOne
    @MapsId("bill_id")
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @Column(name = "quantities")
    private Integer quantities;

    @Column(name = "odered_time")
    private Date orderedTime;

    public BillDetail(BillDetailKey id, Integer quantities, Date ordered_time) {
        super();
        this.id = id;
        this.quantities = quantities;
        this.orderedTime = ordered_time;
    }

    public Double subTotal() {
        return menuItem.getPrice() * this.quantities;
    }
}
