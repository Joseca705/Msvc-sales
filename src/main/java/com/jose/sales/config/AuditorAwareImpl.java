package com.jose.sales.config;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class AuditorAwareImpl implements AuditorAware<Integer> {

  @Override
  public Optional<Integer> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext()
      .getAuthentication();

    if (
      authentication == null ||
      !authentication.isAuthenticated() ||
      authentication instanceof Jwt
    ) {
      return Optional.empty();
    }

    Jwt jwt = (Jwt) authentication.getPrincipal();
    return Optional.of(Integer.parseInt(jwt.getClaim("sub")));
  }
}
