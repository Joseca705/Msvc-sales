package com.jose.sales.infraestructure.client;

import com.jose.sales.api.model.request.UpdateAmountBatchRequest;
import com.jose.sales.api.model.response.BatchStockSimpleInfo;
import com.jose.sales.api.model.response.UpdatedStockResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-store")
public interface BatchStockClient {
  @GetMapping(path = "/batch/simple-info")
  List<BatchStockSimpleInfo> getBatchStockSimpleInfo(
    @RequestParam List<Integer> ids
  );

  @PutMapping(path = "/batch/less")
  UpdatedStockResponse updateStockAmount(
    @RequestBody UpdateAmountBatchRequest request
  );
}
