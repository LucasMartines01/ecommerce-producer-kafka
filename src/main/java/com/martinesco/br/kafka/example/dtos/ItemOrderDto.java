package com.martinesco.br.kafka.example.dtos;

import jakarta.validation.constraints.NotNull;

public record ItemOrderDto(@NotNull Long idItem,@NotNull Integer quantity) {
}
