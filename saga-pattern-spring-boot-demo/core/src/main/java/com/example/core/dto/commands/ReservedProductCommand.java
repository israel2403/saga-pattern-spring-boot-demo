package com.example.core.dto.commands;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservedProductCommand {

  private UUID productId;
  private Integer productQuantity;
  private UUID orderId;
}
