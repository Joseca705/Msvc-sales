package com.jose.sales.infraestructure.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class UserIdJwtHelper {

  public String getCurrentUserId() {
    Authentication authentication = SecurityContextHolder.getContext()
      .getAuthentication();

    if (
      authentication == null ||
      !authentication.isAuthenticated() ||
      authentication instanceof Jwt
    ) {
      return null;
    }

    Jwt jwt = (Jwt) authentication.getPrincipal();
    return jwt.getClaim("sub");
  }
}
