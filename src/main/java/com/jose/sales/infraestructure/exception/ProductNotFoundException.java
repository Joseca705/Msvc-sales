package com.jose.sales.infraestructure.exception;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException() {
    super("Product was not found in batch");
  }
}
