package com.huerta.orders.service;

import com.huerta.core.types.OrderStatus;
import com.huerta.orders.dto.OrderHistory;
import java.util.List;
import java.util.UUID;

public interface OrderHistoryService {
  void add(UUID orderId, OrderStatus orderStatus);

  List<OrderHistory> findByOrderId(UUID orderId);
}
