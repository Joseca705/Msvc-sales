package com.jose.sales.infraestructure.client;

import com.jose.sales.api.model.response.BatchStockSimpleInfo;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-store")
public interface BatchStockClient {
  @GetMapping(path = "/batch/simple-info")
  List<BatchStockSimpleInfo> getBatchStockSimpleInfo(
    @RequestParam List<Integer> ids
  );
}
