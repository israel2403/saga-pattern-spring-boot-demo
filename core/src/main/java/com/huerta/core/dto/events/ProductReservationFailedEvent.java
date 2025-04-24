package com.huerta.core.dto.events;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductReservationFailedEvent {

  private UUID productId;
  private UUID orderId;
  private Integer productQuantity;
}
