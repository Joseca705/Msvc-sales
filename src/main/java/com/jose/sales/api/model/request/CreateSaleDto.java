package com.jose.sales.api.model.request;

import jakarta.validation.constraints.Digits;
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

  @NotNull(message = "El campo productId no debe ser nulo.")
  @Min(value = 1, message = "El valor de productId no debe ser menor a 1.")
  private Integer productId;

  @NotNull(message = "El campo batchId no debe ser nulo.")
  @Min(value = 1, message = "El valor de batchId no debe ser menor a 1.")
  private Integer batchId;

  @NotNull(message = "El campo amount no debe ser nulo.")
  @Min(value = 1, message = "El valor de amount no debe ser menor a 1.")
  private Integer amount;

  @NotNull(message = "El campo unitSalePrice no debe ser nulo.")
  @Min(value = 1, message = "El valor de unitSalePrice no debe ser menor a 1.")
  @Digits(
    integer = 34,
    fraction = 4,
    message = "El numero de unitSalePrice puede contener hasta 4 decimales."
  )
  private BigDecimal unitSalePrice;
}
