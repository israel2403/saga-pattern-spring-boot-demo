package com.huerta.core.dto.events;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentFailedEvent {

  private UUID orderId;
  private UUID productId;
  private Integer productQuantity;
}
