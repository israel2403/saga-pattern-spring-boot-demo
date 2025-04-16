package com.appsdeveloperblog.product_service.service.handler;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.product_service.service.ProductService;
import com.example.core.dto.Product;
import com.example.core.dto.commands.ReservedProductCommand;
import com.example.core.dto.events.ProductReservationFailedEvent;
import com.example.core.dto.events.ProductReservedEvent;
import com.example.core.exceptions.ProductInsufficientQuantityException;

import lombok.RequiredArgsConstructor;

@Component
@KafkaListener(topicPattern = "${products.commands.topic.name}")
@RequiredArgsConstructor
public class ProductCommandsHandler {

  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ProductCommandsHandler.class);

  private final ProductService productService;

  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Value("${products.events.topic.name}")
  private String productEventsTopicName;

  @KafkaHandler
  public void handleCommand(@Payload final ReservedProductCommand reservedProductCommand) {
    try {
      final Product desiredProduct = new Product(
          reservedProductCommand.getProductId(), reservedProductCommand.getProductQuantity());
      final Product reservedProduct = this.productService.reserve(desiredProduct, reservedProductCommand.getOrderId());
      final ProductReservedEvent productReservedEvent = new ProductReservedEvent(
          reservedProductCommand.getOrderId(),
          reservedProduct.getId(),
          reservedProduct.getPrice(),
          reservedProduct.getQuantity());
      this.kafkaTemplate.send(productEventsTopicName, productReservedEvent);
      LOGGER.info("Product reserved: {}", productReservedEvent);
    } catch (ProductInsufficientQuantityException e) {
      LOGGER.error("Error reserving product: {}", e.getMessage());
    } catch (Exception e) {
      LOGGER.error("Error reserving product: {}", e.getMessage());
      final ProductReservationFailedEvent productReservationFailedEvent = new ProductReservationFailedEvent(
          reservedProductCommand.getProductId(),
          reservedProductCommand.getOrderId(),
          reservedProductCommand.getProductQuantity());
      this.kafkaTemplate.send(
          productEventsTopicName, productReservationFailedEvent);
      LOGGER.info("Product reservation failed: {}", productReservationFailedEvent);
    }
  }
}
