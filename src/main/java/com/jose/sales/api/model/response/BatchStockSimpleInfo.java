package com.jose.sales.api.model.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchStockSimpleInfo {

  private Integer id;

  private Integer initialAmount;

  private BigDecimal purchasePrice;

  private Integer productId;
}
