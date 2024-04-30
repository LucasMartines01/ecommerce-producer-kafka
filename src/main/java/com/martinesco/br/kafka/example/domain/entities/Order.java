package com.martinesco.br.kafka.example.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customer;
    private String address;
    private String email;
    private String phone;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemOrder> items = new ArrayList<>();
    private Double total;

    public Double getTotal() {
        return items.stream().mapToDouble(itemOrder -> itemOrder.getItem().getPrice() * itemOrder.getQuantity()).sum();
    }
}
