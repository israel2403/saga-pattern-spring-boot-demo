package com.appsdeveloperblog.orders_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.orders_service.dao.jpa.entity.OrderEntity;
import com.appsdeveloperblog.orders_service.dao.jpa.repository.OrderRepository;
import com.example.core.dto.Order;
import com.example.core.dto.events.OrderCreatedEvent;
import com.example.core.types.OrderStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${orders.events.topic.name}")
    private String ordersEventsTopicName;

    @Override
    public Order placeOrder(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setCustomerId(order.getCustomerId());
        entity.setProductId(order.getProductId());
        entity.setProductQuantity(order.getProductQuantity());
        entity.setStatus(OrderStatus.CREATED);
        orderRepository.save(entity);

        final OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(
                entity.getId(),
                entity.getCustomerId(),
                entity.getProductId(),
                entity.getProductQuantity());

        kafkaTemplate.send(ordersEventsTopicName, orderCreatedEvent);

        return new Order(
                entity.getId(),
                entity.getCustomerId(),
                entity.getProductId(),
                entity.getProductQuantity(),
                entity.getStatus());
    }

}
