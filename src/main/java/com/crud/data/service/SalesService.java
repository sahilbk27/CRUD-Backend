package com.crud.data.service;


import com.crud.data.model.Sales;
import com.crud.data.repository.SalesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    @Autowired
    private SalesRepo repo;


    public List<Sales> getAllSales() {
        return repo.findAll();
    }

    public Sales getOrderById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Sales addOrder(Sales sales) {
        String orderNumber = generateOrderNumber();
        sales.setOrder_number(orderNumber);
        return repo.save(sales);
    }
    private String generateOrderNumber() {
        // Format: ORD-2026-001, ORD-2026-002 ...
        int year = java.time.Year.now().getValue();
        long count = repo.count() + 1;
        return String.format("ORD-%d-%03d", year, count);
    }


    public Sales updateOrder(int id, Sales sales) {
        return repo.save(sales);
    }

    public void deleteOrder(int id) {
        repo.deleteById(id);
    }

    public List<Sales> searchOrders(String keyword) {
        return repo.searchOrders(keyword);
    }
}
