package com.jose.sales.config;

import com.jose.sales.infraestructure.client.error_handler.FeignErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

  @Bean
  ErrorDecoder errorDecoder() {
    return new FeignErrorDecoder();
  }
}
