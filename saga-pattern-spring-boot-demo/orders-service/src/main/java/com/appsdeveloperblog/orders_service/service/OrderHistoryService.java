package com.appsdeveloperblog.orders_service.service;

import java.util.List;
import java.util.UUID;

import com.appsdeveloperblog.core.types.OrderStatus;
import com.appsdeveloperblog.orders_service.dto.OrderHistory;

public interface OrderHistoryService {
    void add(UUID orderId, OrderStatus orderStatus);

    List<OrderHistory> findByOrderId(UUID orderId);
}
