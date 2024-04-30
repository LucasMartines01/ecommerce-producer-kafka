package com.martinesco.br.kafka.example.dtos;

import com.martinesco.br.kafka.example.domain.entities.ItemOrder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @NotBlank
    private String customer;
    @NotBlank
    private String address;
    @Email
    private String email;
    @NotBlank
    private String phone;
    @NotNull
    private List<ItemOrderDto> items = new ArrayList<>();
}
