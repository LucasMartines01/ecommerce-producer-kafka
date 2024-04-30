package com.martinesco.br.kafka.example.controllers;

import com.martinesco.br.kafka.example.domain.services.OrderService;
import com.martinesco.br.kafka.example.dtos.OrderDto;
import com.martinesco.br.kafka.example.dtos.ResponseOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<Void> createOrder(@RequestBody OrderDto orderDto) {
        orderService.saveOrder(orderDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrderDto> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponseOrderDto>> getAll() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }


}
