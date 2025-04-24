package com.huerta.core.dto.events;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Event representing the creation of an order. */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderCreatedEvent {
  /** The unique identifier of the order. */
  private UUID orderId;

  /** The unique identifier of the customer who placed the order. */
  private UUID customerId;

  /** The unique identifier of the product ordered. */
  private UUID productId;

  /** The quantity of the product ordered. */
  private Integer productQuantity;
}
