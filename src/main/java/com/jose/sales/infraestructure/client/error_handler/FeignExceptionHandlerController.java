package com.jose.sales.infraestructure.client.error_handler;

import com.jose.sales.api.model.response.ErrorResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FeignExceptionHandlerController {

  @ExceptionHandler(exception = FeignException.class)
  public ErrorResponse handleFeignException(FeignException exception) {
    return ErrorResponse.builder()
      .error("Servicio interno no disponible, vuelva a intentarlo luego.")
      .status(HttpStatus.SERVICE_UNAVAILABLE.name())
      .code(HttpStatus.SERVICE_UNAVAILABLE.value())
      .build();
  }
}
