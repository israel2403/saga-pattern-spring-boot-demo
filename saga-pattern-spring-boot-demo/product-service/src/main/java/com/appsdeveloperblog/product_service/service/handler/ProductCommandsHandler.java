package com.appsdeveloperblog.product_service.service.handler;

import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.product_service.service.ProductService;
import com.example.core.dto.Product;
import com.example.core.dto.commands.ReservedProductCommand;

import lombok.RequiredArgsConstructor;

@Component
@KafkaListener(topicPattern = "${products.commands.topic.name}")
@RequiredArgsConstructor
public class ProductCommandsHandler {

  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ProductCommandsHandler.class);

  private final ProductService productService;

  @KafkaHandler
  public void handleCommand(@Payload final ReservedProductCommand reservedProductCommand) {
    try {
      final Product desiredProduct = new Product(reservedProductCommand.getProductId(),
          reservedProductCommand.getProductQuantity());
      final Product reservedProduct = this.productService.reserve(desiredProduct,
          reservedProductCommand.getOrderId());

    } catch (Exception e) {
      LOGGER.error("Error reserving product: {}", e.getMessage());
    }
  }
}
