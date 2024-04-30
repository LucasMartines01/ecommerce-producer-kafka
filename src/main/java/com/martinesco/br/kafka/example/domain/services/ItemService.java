package com.martinesco.br.kafka.example.domain.services;

import com.martinesco.br.kafka.example.domain.repositories.ItemRepository;
import com.martinesco.br.kafka.example.domain.entities.Item;
import com.martinesco.br.kafka.example.dtos.ItemDto;
import com.martinesco.br.kafka.example.dtos.UpdateItemDto;
import com.martinesco.br.kafka.example.dtos.ResponseItemDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    ModelMapper modelMapper;

    public void save(ItemDto item) {
        if(itemRepository.existsByName(item.getName())) {
            throw new EntityExistsException("Item already exists");
        }
        Item itemEntity = modelMapper.map(item, Item.class);
        itemRepository.save(itemEntity);
    }

    public void update(Long id, UpdateItemDto item) {
        Item itemEntity = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item not found"));
        modelMapper.map(item, itemEntity);
        itemRepository.save(itemEntity);
    }

    public void delete(Long id) {
        Item itemEntity = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item not found"));
        itemRepository.delete(itemEntity);
    }

    public ResponseItemDto findById(Long id) {
        Item itemEntity = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item not found"));
        return modelMapper.map(itemEntity, ResponseItemDto.class);
    }

    public List<ResponseItemDto> findAll() {
        return itemRepository.findAll().stream().map(item -> modelMapper.map(item, ResponseItemDto.class)).collect(Collectors.toList());
    }
}
