package com.appsdeveloperblog.orders_service.saga;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.core.dto.commands.ReservedProductCommand;
import com.example.core.dto.events.OrderCreatedEvent;

import lombok.RequiredArgsConstructor;

@Component
@KafkaListener(topics = { "${orders.events.topic.name}" })
@RequiredArgsConstructor
public class OrderSaga {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Value("${products.commands.topic.name}")
  private String productsCommandsTopicName;

  @KafkaHandler
  public void handleEvent(@Payload final OrderCreatedEvent orderCreatedEvent) {
    final ReservedProductCommand reservedProductCommand = new ReservedProductCommand(
        orderCreatedEvent.getProductId(),
        orderCreatedEvent.getProductQuantity(),
        orderCreatedEvent.getOrderId());

    kafkaTemplate.send(productsCommandsTopicName, reservedProductCommand);
  }
}
