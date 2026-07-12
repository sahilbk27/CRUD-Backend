package com.crud.data.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Data @AllArgsConstructor @NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    @JsonProperty("customer_name")
    private String customer_name;

    @Column(name = "customer_email")
    @JsonProperty("customer_email")
    private String customer_email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;
}
