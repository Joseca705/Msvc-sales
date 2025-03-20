package com.jose.sales.infraestructure.client.error_handler;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    if (response.status() == 400) {
      return new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "Los datos introducidos no son correctos."
      );
    }
    return new ResponseStatusException(
      HttpStatus.INTERNAL_SERVER_ERROR,
      "Internal Server Error"
    );
  }
}
