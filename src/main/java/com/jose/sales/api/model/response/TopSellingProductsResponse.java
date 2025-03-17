package com.jose.sales.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopSellingProductsResponse {

  private Integer id;

  private String name;

  private Integer totalAmount;
}
