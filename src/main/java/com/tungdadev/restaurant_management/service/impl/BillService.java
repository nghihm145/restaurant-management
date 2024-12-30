package com.tungdadev.restaurant_management.service.impl;

import com.tungdadev.restaurant_management.model.Bill;
import com.tungdadev.restaurant_management.repository.BillRepository;
import com.tungdadev.restaurant_management.service.IBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 10:12
 */
@Service("billService")
@Transactional
@RequiredArgsConstructor
public class BillService implements IBillService {
    private final BillRepository repository;

    public Bill findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void saveBill(Bill bill) {
        repository.save(bill);
    }

    public void updateBill(Bill bill) {
        saveBill(bill);
    }
}
