package com.crud.data.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    @JsonProperty("order_number")
    private String order_number;

    @Column(name = "customer_name")
    @JsonProperty("customer_name")
    private String customer_name;

    @Column(name = "customer_email")
    @JsonProperty("customer_email")
    private String customer_email;

    @Column(name = "product_name")
    @JsonProperty("product_name")
    private String product_name;

    @Column(name = "product_code")
    @JsonProperty("product_code")
    private String product_code;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit")
    private String unit;

    @Column(name = "unit_price")
    @JsonProperty("unit_price")
    private double unit_price;

    @Column(name = "total_amount")
    @JsonProperty("total_amount")
    private double total_amount;

    @Column(name = "delivery_address")
    @JsonProperty("delivery_address")
    private String delivery_address;

    @Column(name = "expected_delivery")
    @JsonProperty("expected_delivery")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expected_delivery;

    @Column(name = "status")
    private String status;

    @Column(name = "order_date")
    @JsonProperty("order_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date order_date;

}
