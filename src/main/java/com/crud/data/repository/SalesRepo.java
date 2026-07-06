package com.crud.data.repository;

import com.crud.data.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepo extends JpaRepository<Sales,Integer> {

    @Query("SELECT s from Sales s WHERE " +
            "LOWER(s.customer_name) LIKE  LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.product_name) LIKE  LOWER(CONCAT('%', :keyword, '%')) "
    )
    List<Sales> searchOrders(String keyword);
}
