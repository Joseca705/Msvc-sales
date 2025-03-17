package com.jose.sales.api.model.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateKardexRequest {

  private String movementType;

  private Integer amount;

  private Integer balanceAmount;

  private BigDecimal unitPrice;

  private String reference;

  private Integer referenceId;

  private Integer productId;

  private Integer batchId;
}
