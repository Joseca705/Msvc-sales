package com.jose.sales.infraestructure.client;

import com.jose.sales.api.model.request.IdBatchProductRequest;
import com.jose.sales.api.model.response.PurchasePriceBatchStore;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-store")
public interface StoreClient {
  @PostMapping(path = "/batch/batch-prices")
  List<PurchasePriceBatchStore> getPurchasePriceBatchStore(
    @RequestBody List<IdBatchProductRequest> ids
  );
}
