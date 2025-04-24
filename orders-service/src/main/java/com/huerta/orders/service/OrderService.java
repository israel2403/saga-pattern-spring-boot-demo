package com.huerta.orders.service;

import com.huerta.core.dto.Order;

public interface OrderService {
  Order placeOrder(Order order);
}
