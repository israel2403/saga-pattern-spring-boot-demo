package com.huerta.core.dto.commands;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductReservationCancelledEvent {

  private UUID productId;
  private UUID orderId;
}
