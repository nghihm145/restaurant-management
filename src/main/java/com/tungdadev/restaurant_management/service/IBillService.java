package com.tungdadev.restaurant_management.service;

import com.tungdadev.restaurant_management.model.Bill;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 10:12
 */
public interface IBillService {
    Bill findById(Long id);

    void saveBill(Bill bill);

    void updateBill(Bill bill);
}
