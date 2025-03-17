package com.jose.sales.config.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenPropagator implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate template) {
    Authentication authentication = SecurityContextHolder.getContext()
      .getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      Jwt jwt = (Jwt) authentication.getPrincipal();
      String jwtToken = jwt.getTokenValue();
      template.header("Authorization", "Bearer " + jwtToken);
    }
  }
}
