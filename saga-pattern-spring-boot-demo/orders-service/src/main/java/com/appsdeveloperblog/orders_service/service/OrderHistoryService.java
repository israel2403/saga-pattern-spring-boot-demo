package com.appsdeveloperblog.orders_service.service;

import java.util.List;
import java.util.UUID;

import com.appsdeveloperblog.orders_service.dto.OrderHistory;
import com.example.core.types.OrderStatus;

public interface OrderHistoryService {
    void add(UUID orderId, OrderStatus orderStatus);

    List<OrderHistory> findByOrderId(UUID orderId);
}
