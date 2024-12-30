package com.tungdadev.restaurant_management.repository;

import com.tungdadev.restaurant_management.model.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 10:22
 */
@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {

}
