package com.appsdeveloperblog.orders_service.saga;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.core.dto.events.OrderCreatedEvent;

@Component
@KafkaListener(topics = { "${orders.events.topic.name}" })
public class OrderSaga {

  @KafkaHandler
  public void handleEvent(@Payload final OrderCreatedEvent orderCreatedEvent) {

  }
}
