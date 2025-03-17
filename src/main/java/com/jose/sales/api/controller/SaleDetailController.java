package com.jose.sales.api.controller;

import com.jose.sales.api.model.response.TopSellingProductsResponse;
import com.jose.sales.infraestructure.abstract_service.ISaleDetailService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/sale-detail")
public class SaleDetailController {

  private final ISaleDetailService saleDetailService;

  @GetMapping(path = "/top-selling-products")
  public ResponseEntity<List<TopSellingProductsResponse>> getTopSellingProducts(
    @RequestParam(name = "limit", defaultValue = "3") Integer limit
  ) {
    List<TopSellingProductsResponse> topProducts =
      this.saleDetailService.findTopSellingProducts(limit);
    return ResponseEntity.ok(topProducts);
  }
}
