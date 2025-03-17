package com.jose.sales.infraestructure.abstract_service;

import com.jose.sales.api.model.request.CreateSaleDetail;
import com.jose.sales.api.model.response.CreatedSaleDetail;
import com.jose.sales.api.model.response.TopSellingProductsResponse;
import java.util.List;

public interface ISaleDetailService
  extends CrudService<CreateSaleDetail, CreatedSaleDetail, Integer> {
  List<TopSellingProductsResponse> findTopSellingProducts(Integer limit);
}
