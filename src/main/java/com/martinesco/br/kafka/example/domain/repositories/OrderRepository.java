package com.martinesco.br.kafka.example.domain.repositories;

import com.martinesco.br.kafka.example.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{
}
