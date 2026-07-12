package com.crud.data.repository;

import com.crud.data.model.Sales;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepo extends JpaRepository<Sales,Integer> {

    @Override
    @EntityGraph(attributePaths = {"customer", "product"})
    List<Sales> findAll();

    @EntityGraph(attributePaths = {"customer", "product"})
    @Query("SELECT s from Sales s WHERE " +
            "LOWER(s.customer.customer_name) LIKE  LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.product.product_name) LIKE  LOWER(CONCAT('%', :keyword, '%')) "
    )
    List<Sales> searchOrders(String keyword);
}
