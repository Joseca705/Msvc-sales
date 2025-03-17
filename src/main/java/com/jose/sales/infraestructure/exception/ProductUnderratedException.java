package com.jose.sales.infraestructure.exception;

public class ProductUnderratedException extends RuntimeException {

  private static String message = "The product with id: %s is underrated.";

  public ProductUnderratedException(Integer productId) {
    super(String.format(message, productId));
  }
}
