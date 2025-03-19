package com.jose.sales.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentAmountBatchKardex {

  private Integer batchId;

  private Integer balanceAmount;
}
