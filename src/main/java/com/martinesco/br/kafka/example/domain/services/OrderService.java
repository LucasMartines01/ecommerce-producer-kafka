package com.martinesco.br.kafka.example.domain.services;

import com.martinesco.br.kafka.example.domain.entities.Item;
import com.martinesco.br.kafka.example.domain.entities.ItemOrder;
import com.martinesco.br.kafka.example.domain.entities.Order;
import com.martinesco.br.kafka.example.domain.exceptions.BusinessException;
import com.martinesco.br.kafka.example.domain.repositories.ItemRepository;
import com.martinesco.br.kafka.example.domain.repositories.OrderRepository;
import com.martinesco.br.kafka.example.dtos.*;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    private final ItemRepository itemRepository;

    private final NotificationService notificationService;

    public OrderService(ModelMapper modelMapper, ItemRepository itemRepository, NotificationService notificationService, OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.itemRepository = itemRepository;
        this.notificationService = notificationService;
        this.orderRepository = orderRepository;
    }


    public void saveOrder(OrderDto orderDto) {
        Order order = new Order();
        List<ItemOrder> itemOrders = orderDto.getItems().stream().map(itemOrderDto -> {
            Item item = itemRepository.findById(itemOrderDto.idItem()).orElseThrow(() -> new EntityNotFoundException("Item not found"));

            if (item.getQuantityInStock() < itemOrderDto.quantity()) {
                throw new BusinessException("Item quantity not available");
            }
            if(itemOrderDto.quantity() <= 0) {
                throw new BusinessException("Item quantity must be greater than 0");
            }
            ItemOrder itemOrder = new ItemOrder();
            itemOrder.setItem(item);
            itemOrder.setQuantity(itemOrderDto.quantity());
            itemOrder.setOrder(order);
            return itemOrder;
        }).toList();

        order.setItems(itemOrders);
        order.setCustomer(orderDto.getCustomer());
        order.setAddress(orderDto.getAddress());
        order.setEmail(orderDto.getEmail());
        order.setPhone(orderDto.getPhone());
        orderRepository.save(order);

        notificationService.sendEmail(
                new EmailDTO(
                        order.getEmail(),
                        "Order Confirmation",
                        "Your order has been placed successfully"
                ));
    }

    public ResponseOrderDto getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));

        return new ResponseOrderDto(order.getId(),
                order.getCustomer(),
                order.getAddress(),
                order.getEmail(), order.getPhone(),
                order.getItems().stream().map(itemOrder ->
                        new ResponseItemOrderDto(modelMapper.map(itemOrder.getItem(), ResponseItemDto.class),
                                itemOrder.getQuantity())).toList(),
                order.getTotal()
        );
    }

    public List<ResponseOrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(order -> {
            return new ResponseOrderDto(order.getId(),
                    order.getCustomer(),
                    order.getAddress(),
                    order.getEmail(), order.getPhone(),
                    order.getItems().stream().map(itemOrder ->
                            new ResponseItemOrderDto(modelMapper.map(itemOrder.getItem(), ResponseItemDto.class),
                                    itemOrder.getQuantity())).toList(),
                    order.getTotal()
            );
        }).toList();
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        orderRepository.delete(order);
    }
}
