package com.example.core.dto.events;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Event representing the creation of an order.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderCreatedEvent {
    /**
     * The unique identifier of the order.
     */
    private UUID orderId;

    /**
     * The unique identifier of the customer who placed the order.
     */
    private UUID customerId;

    /**
     * The unique identifier of the product ordered.
     */
    private UUID productId;

    /**
     * The quantity of the product ordered.
     */
    private Integer productQuantity;
}
