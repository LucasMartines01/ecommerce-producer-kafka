package com.martinesco.br.kafka.example.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    @NotBlank
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private Integer quantityInStock;
    @NotBlank
    private String description;
}
