package com.appsdeveloperblog.orders_service.service;

import org.springframework.stereotype.Service;

import com.appsdeveloperblog.core.dto.Order;
import com.appsdeveloperblog.core.types.OrderStatus;
import com.appsdeveloperblog.orders_service.dao.jpa.entity.OrderEntity;
import com.appsdeveloperblog.orders_service.dao.jpa.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setCustomerId(order.getCustomerId());
        entity.setProductId(order.getProductId());
        entity.setProductQuantity(order.getProductQuantity());
        entity.setStatus(OrderStatus.CREATED);
        orderRepository.save(entity);

        return new Order(
                entity.getId(),
                entity.getCustomerId(),
                entity.getProductId(),
                entity.getProductQuantity(),
                entity.getStatus());
    }

}
