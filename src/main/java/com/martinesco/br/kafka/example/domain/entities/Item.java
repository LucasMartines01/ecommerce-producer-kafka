package com.martinesco.br.kafka.example.domain.entities;

import com.martinesco.br.kafka.example.dtos.ItemDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantityInStock;
}
