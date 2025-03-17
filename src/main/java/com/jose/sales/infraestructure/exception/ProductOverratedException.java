package com.jose.sales.infraestructure.exception;

public class ProductOverratedException extends RuntimeException {

  private static String message = "The product with id: %s is overrated.";

  public ProductOverratedException(Integer productId) {
    super(String.format(message, productId));
  }
}
