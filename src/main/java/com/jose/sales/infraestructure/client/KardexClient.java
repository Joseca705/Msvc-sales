package com.jose.sales.infraestructure.client;

import com.jose.sales.api.model.request.CreateKardexRequest;
import com.jose.sales.api.model.response.CreatedKardexResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-kardex")
public interface KardexClient {
  @PostMapping(path = "/kardex")
  CreatedKardexResponse saveSaleIntoKardex(
    @RequestBody List<CreateKardexRequest> request
  );
}
