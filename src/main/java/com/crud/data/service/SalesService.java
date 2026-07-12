package com.crud.data.service;


import com.crud.data.model.Customer;
import com.crud.data.model.Product;
import com.crud.data.model.Sales;
import com.crud.data.repository.CustomerRepo;
import com.crud.data.repository.ProductRepo;
import com.crud.data.repository.SalesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    @Autowired
    private SalesRepo repo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductRepo productRepo;


    public List<Sales> getAllSales() {
        return repo.findAll();
    }

    public Sales getOrderById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Sales addOrder(Sales sales) {
        if (sales.getCustomer() != null && sales.getCustomer().getId() != null) {
            Customer customer = customerRepo.findById(sales.getCustomer().getId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            sales.setCustomer(customer);
        }

        if (sales.getProduct() != null && sales.getProduct().getId() != null) {
            Product product = productRepo.findById(sales.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            sales.setProduct(product);
        }

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
        Sales existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Resolve customer
        if (sales.getCustomer() != null && sales.getCustomer().getId() != null) {
            Customer customer = customerRepo.findById(sales.getCustomer().getId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            existing.setCustomer(customer);
        }

        // Resolve product
        if (sales.getProduct() != null && sales.getProduct().getId() != null) {
            Product product = productRepo.findById(sales.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            existing.setProduct(product);
        }

        existing.setQuantity(sales.getQuantity());
        existing.setTotal_amount(sales.getTotal_amount());
        existing.setDelivery_address(sales.getDelivery_address());
        existing.setExpected_delivery(sales.getExpected_delivery());
        existing.setStatus(sales.getStatus());
        existing.setOrder_date(sales.getOrder_date());

        return repo.save(existing);
    }

    public void deleteOrder(int id) {
        repo.deleteById(id);
    }

    public List<Sales> searchOrders(String keyword) {
        return repo.searchOrders(keyword);
    }
}
