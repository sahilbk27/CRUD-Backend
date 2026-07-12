package com.crud.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data @AllArgsConstructor @NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    @JsonProperty("product_name")
    private String product_name;

    @Column(name = "product_code")
    @JsonProperty("product_code")
    private String product_code;

    @Column(name = "unit")
    private String unit;

    @Column(name = "unit_price")
    @JsonProperty("unit_price")
    private Double unit_price;
}
