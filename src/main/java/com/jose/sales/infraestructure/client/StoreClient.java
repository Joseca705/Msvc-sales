package com.jose.sales.infraestructure.client;

import com.jose.sales.api.model.response.PurchasePriceBatchStore;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-store")
public interface StoreClient {
  @GetMapping(path = "/batch/batch-prices")
  List<PurchasePriceBatchStore> getPurchasePriceBatchStore(
    @RequestParam List<Integer> ids
  );
}
