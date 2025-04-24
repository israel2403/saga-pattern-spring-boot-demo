package com.huerta.core.dto.events;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductReservedEvent {

  private UUID orderId;
  private UUID productId;
  private BigDecimal productPrice;
  private Integer productQuantity;
}
