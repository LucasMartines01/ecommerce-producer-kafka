package com.martinesco.br.kafka.example.controllers;

import com.martinesco.br.kafka.example.dtos.ItemDto;
import com.martinesco.br.kafka.example.dtos.UpdateItemDto;
import com.martinesco.br.kafka.example.dtos.ResponseItemDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.martinesco.br.kafka.example.domain.services.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public ResponseEntity<List<ResponseItemDto>> getItems() {
        return itemService.findAll().isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseItemDto> getItem(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Void> createItem(@RequestBody @Valid ItemDto item) {
        itemService.save(item);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(@PathVariable Long id,@RequestBody UpdateItemDto item) {
        itemService.update(id, item);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.ok().build();
    }

}
