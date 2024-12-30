package com.tungdadev.restaurant_management.service;

import com.tungdadev.restaurant_management.model.BillDetail;

import java.util.List;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 10:20
 */
public interface IBillDetailService {
    BillDetail findById(Long id);

    List<BillDetail> findAllBillDetails();

    void saveBillDetail(BillDetail billDetail);

    void updateBillDetail(BillDetail billDetail);

    void deleteBillDetailById(Long Id);

    void deleteAllBillDetails();
}
