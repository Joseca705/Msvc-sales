package com.jose.sales.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAmountBatchRequest {

  private Integer id;

  private Integer amount;
}
