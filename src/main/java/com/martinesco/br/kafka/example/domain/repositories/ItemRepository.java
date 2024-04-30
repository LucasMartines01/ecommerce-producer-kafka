package com.martinesco.br.kafka.example.domain.repositories;

import com.martinesco.br.kafka.example.domain.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByName(String name);
}
