package com.martinesco.br.kafka.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseOrderDto {
    private Long id;
    private String customer;
    private String address;
    private String email;
    private String phone;
    private List<ResponseItemOrderDto> items;
    private Double total;
}
