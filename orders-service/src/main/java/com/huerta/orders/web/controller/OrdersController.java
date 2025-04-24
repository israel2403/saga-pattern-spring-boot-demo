package com.huerta.orders.web.controller;

import com.huerta.core.dto.Order;
import com.huerta.orders.dto.CreateOrderRequest;
import com.huerta.orders.dto.CreateOrderResponse;
import com.huerta.orders.dto.OrderHistoryResponse;
import com.huerta.orders.service.OrderHistoryService;
import com.huerta.orders.service.OrderService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {
  private final OrderService orderService;
  private final OrderHistoryService orderHistoryService;

  public OrdersController(OrderService orderService, OrderHistoryService orderHistoryService) {
    this.orderService = orderService;
    this.orderHistoryService = orderHistoryService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.ACCEPTED)
  public CreateOrderResponse placeOrder(@RequestBody @Valid CreateOrderRequest request) {
    var order = new Order();
    BeanUtils.copyProperties(request, order);
    Order createdOrder = orderService.placeOrder(order);

    var response = new CreateOrderResponse();
    BeanUtils.copyProperties(createdOrder, response);
    return response;
  }

  @GetMapping("/{orderId}/history")
  @ResponseStatus(HttpStatus.OK)
  public List<OrderHistoryResponse> getOrderHistory(@PathVariable UUID orderId) {
    return orderHistoryService.findByOrderId(orderId).stream()
        .map(
            orderHistory -> {
              OrderHistoryResponse orderHistoryResponse = new OrderHistoryResponse();
              BeanUtils.copyProperties(orderHistory, orderHistoryResponse);
              return orderHistoryResponse;
            })
        .toList();
  }
}
