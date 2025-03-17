package com.jose.sales.api.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSaleDto {

  @NotNull
  @Min(1)
  private Integer productId;

  @NotNull
  @Min(1)
  private Integer batchId;

  @NotNull
  @Min(1)
  private Integer amount;

  @NotNull
  @Min(1)
  private BigDecimal unitSalePrice;
}
