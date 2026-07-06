package com.crud.data.controller;


import com.crud.data.model.Sales;
import com.crud.data.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class SalesController {

    @Autowired
    private SalesService service;

    @GetMapping("/orders")
    public ResponseEntity<List<Sales>> getAllSales(){
        return new ResponseEntity<>(service.getAllSales(), HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Sales> getOrderById(@PathVariable int id){
        Sales sales = service.getOrderById(id);

        if(sales != null)
            return new ResponseEntity<>(sales,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/orders")
    public ResponseEntity<?> addOrder(@RequestBody Sales sales){
        try{
            Sales sale = service.addOrder(sales);
            return new ResponseEntity<>(sale,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/orders/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable int id, @RequestBody Sales sales){
        Sales sales1 = null;

        sales1 = service.updateOrder (id,sales);

        if(sales1 != null)
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed To Update",HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id){
        Sales sales = service.getOrderById(id);

        if(sales !=null){
            service.deleteOrder(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Order Not Found",HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/orders/search")
    public ResponseEntity<List<Sales>> searchOrders(@RequestParam String keyword){
        List<Sales> products = service.searchOrders(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }





}
