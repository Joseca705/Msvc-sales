package com.jose.sales.infraestructure.abstract_service;

import com.jose.sales.api.model.request.CreateSaleDto;
import com.jose.sales.api.model.response.CreatedSaleResponse;
import java.util.List;

public interface ISaleService
  extends CrudService<List<CreateSaleDto>, CreatedSaleResponse, Integer> {}
