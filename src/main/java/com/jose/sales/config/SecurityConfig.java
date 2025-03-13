package com.jose.sales.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private static final String[] PUBLIC_RESOURCES = {
    "/tickets/**",
    "/swagger-ui/**",
    "/.well-known/**, ",
    "/v3/api-docs/**",
  };
  private static final String[] USER_RESOURCES = { "/products/**" };
  private static final String[] ADMIN_RESOURCES = { "/user/**", "/report/**" };
  private static final String ROLE_ADMIN = "ADMIN";
  private static final String ROLE_USER = "USER";

  @Bean
  SecurityFilterChain appSecurityFilterChain(HttpSecurity http)
    throws Exception {
    http
      .formLogin(Customizer.withDefaults())
      .authorizeHttpRequests(auth ->
        auth
          .requestMatchers(PUBLIC_RESOURCES)
          .permitAll()
          .requestMatchers(USER_RESOURCES)
          .hasRole(ROLE_USER)
          .requestMatchers(ADMIN_RESOURCES)
          .hasRole(ROLE_ADMIN)
      )
      .oauth2ResourceServer(oauth2 ->
        oauth2.jwt(jwt ->
          jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
        )
      );
    return http.build();
  }

  @Bean
  JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter =
      new JwtGrantedAuthoritiesConverter();
    grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
    grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");

    var converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
    return converter;
  }
}
