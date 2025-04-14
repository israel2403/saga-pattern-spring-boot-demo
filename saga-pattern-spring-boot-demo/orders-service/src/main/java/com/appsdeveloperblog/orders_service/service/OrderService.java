package com.appsdeveloperblog.orders_service.service;

import com.appsdeveloperblog.core.dto.Order;

public interface OrderService {
    Order placeOrder(Order order);
}
