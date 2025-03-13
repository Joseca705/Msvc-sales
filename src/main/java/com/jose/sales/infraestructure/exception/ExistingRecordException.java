package com.jose.sales.infraestructure.exception;

public class ExistingRecordException extends RuntimeException {

  public ExistingRecordException(String message) {
    super(message);
  }
}
