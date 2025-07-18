package com.jose.sales.api.controller.error_handler;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.jose.sales.api.model.response.ErrorResponse;
import com.jose.sales.api.model.response.ErrorsResponse;
import com.jose.sales.api.model.response.abstract_response.BaseErrorResponse;
import com.jose.sales.infraestructure.exception.ExistingRecordException;
import com.jose.sales.infraestructure.exception.ProductNotFoundException;
import com.jose.sales.infraestructure.exception.ProductOverratedException;
import com.jose.sales.infraestructure.exception.ProductUnderratedException;
import jakarta.validation.ConstraintViolationException;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestController {

  @ExceptionHandler(exception = MethodArgumentNotValidException.class)
  public BaseErrorResponse handleFailedValidation(
    MethodArgumentNotValidException exception
  ) {
    var errors = new ArrayList<String>();
    exception
      .getAllErrors()
      .forEach(error -> errors.add(error.getDefaultMessage()));
    return ErrorsResponse.builder()
      .errors(errors)
      .status(HttpStatus.BAD_REQUEST.name())
      .code(HttpStatus.BAD_REQUEST.value())
      .build();
  }

  @ExceptionHandler(exception = ExistingRecordException.class)
  public BaseErrorResponse handleDuplicateRecordException(
    ExistingRecordException exception
  ) {
    return ErrorResponse.builder()
      .error(exception.getMessage())
      .status(HttpStatus.BAD_REQUEST.name())
      .code(HttpStatus.BAD_REQUEST.value())
      .build();
  }

  @ExceptionHandler(exception = ProductNotFoundException.class)
  public BaseErrorResponse handleProductNotFoundException(
    ProductNotFoundException exception
  ) {
    return ErrorResponse.builder()
      .error(exception.getMessage())
      .status(HttpStatus.BAD_REQUEST.name())
      .code(HttpStatus.BAD_REQUEST.value())
      .build();
  }

  @ExceptionHandler(exception = ProductOverratedException.class)
  public BaseErrorResponse handleProductOverratedException(
    ProductOverratedException exception
  ) {
    return ErrorResponse.builder()
      .error(exception.getMessage())
      .status(HttpStatus.BAD_REQUEST.name())
      .code(HttpStatus.BAD_REQUEST.value())
      .build();
  }

  @ExceptionHandler(exception = ProductUnderratedException.class)
  public BaseErrorResponse handleProductUnderratedException(
    ProductUnderratedException exception
  ) {
    return ErrorResponse.builder()
      .error(exception.getMessage())
      .status(HttpStatus.BAD_REQUEST.name())
      .code(HttpStatus.BAD_REQUEST.value())
      .build();
  }

  @ExceptionHandler(exception = UnrecognizedPropertyException.class)
  public ErrorResponse handleUnrecognizedPropertyException(
    UnrecognizedPropertyException exception
  ) {
    return ErrorResponse.builder()
      .error("El modelo de datos enviado no es igual al modelo requerido.")
      .status(HttpStatus.BAD_REQUEST.name())
      .code(HttpStatus.BAD_REQUEST.value())
      .build();
  }

  @ExceptionHandler(exception = ConstraintViolationException.class)
  public ErrorResponse handleConstraintViolationException(
    ConstraintViolationException exception
  ) {
    return ErrorResponse.builder()
      .error(exception.getMessage())
      .status(HttpStatus.BAD_REQUEST.name())
      .code(HttpStatus.BAD_REQUEST.value())
      .build();
  }
}
