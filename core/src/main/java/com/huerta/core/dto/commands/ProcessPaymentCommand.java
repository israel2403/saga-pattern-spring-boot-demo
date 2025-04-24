package com.huerta.core.dto.commands;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProcessPaymentCommand {

  private UUID orderId;
  private UUID productId;
  private BigDecimal productPrice;
  private Integer productQuantity;
}
