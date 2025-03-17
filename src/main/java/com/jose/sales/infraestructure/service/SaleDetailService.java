package com.jose.sales.infraestructure.service;

import com.jose.sales.api.model.request.CreateSaleDetail;
import com.jose.sales.api.model.response.CreatedSaleDetail;
import com.jose.sales.api.model.response.TopSellingProductsResponse;
import com.jose.sales.domain.repository.SaleDetailRepository;
import com.jose.sales.infraestructure.abstract_service.ISaleDetailService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaleDetailService implements ISaleDetailService {

  private final SaleDetailRepository saleDetailRepository;

  @Override
  public CreatedSaleDetail create(CreateSaleDetail request) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

  @Override
  public CreatedSaleDetail read(Integer id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'read'");
  }

  @Override
  public CreatedSaleDetail update(CreateSaleDetail request, Integer id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(Integer id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public List<TopSellingProductsResponse> findTopSellingProducts(
    Integer limit
  ) {
    List<Object[]> rawProducts =
      this.saleDetailRepository.findTopSellingProductsByLimit(limit);

    return rawProducts
      .stream()
      .map(rawProduct ->
        new TopSellingProductsResponse(
          ((Number) rawProduct[0]).intValue(),
          (String) rawProduct[1],
          ((Number) rawProduct[2]).intValue()
        )
      )
      .toList();
  }
}
