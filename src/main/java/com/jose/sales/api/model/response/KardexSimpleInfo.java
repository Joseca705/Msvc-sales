package com.jose.sales.api.model.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KardexSimpleInfo {

  private Integer batchId;

  private Integer balanceAmount;

  private BigDecimal unitPrice;

  private Integer productId;
}
