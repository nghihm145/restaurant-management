package com.tungdadev.restaurant_management.service.impl;

import com.tungdadev.restaurant_management.model.BillDetail;
import com.tungdadev.restaurant_management.repository.BillDetailRepository;
import com.tungdadev.restaurant_management.service.IBillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 10:21
 */
@Service("billDetailService")
@Transactional
@RequiredArgsConstructor
public class BillDetailService implements IBillDetailService {

    private final BillDetailRepository repository;

    @Override
    public BillDetail findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<BillDetail> findAllBillDetails() {
        return repository.findAll();
    }

    @Override
    public void saveBillDetail(BillDetail billDetail) {
        repository.save(billDetail);
    }

    @Override
    public void updateBillDetail(BillDetail billDetail) {
        saveBillDetail(billDetail);
    }

    @Override
    public void deleteBillDetailById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllBillDetails() {
        repository.deleteAll();
    }
}
