package com.tungdadev.restaurant_management.repository;

import com.tungdadev.restaurant_management.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 10:14
 */
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

}
