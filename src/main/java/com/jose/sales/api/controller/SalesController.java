package com.jose.sales.api.controller;

import com.jose.sales.api.model.request.CreateSaleDto;
import com.jose.sales.api.model.response.CreatedSaleResponse;
import com.jose.sales.infraestructure.abstract_service.ISaleService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sale")
public class SalesController {

  private final ISaleService saleService;

  @PostMapping(path = "")
  public ResponseEntity<CreatedSaleResponse> createSale(
    @RequestBody(required = true) @Valid List<CreateSaleDto> products
  ) {
    return ResponseEntity.ok(this.saleService.create(products));
  }
}
