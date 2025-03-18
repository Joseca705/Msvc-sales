package com.jose.sales.infraestructure.client;

import com.jose.sales.api.model.request.CreateKardexRequest;
import com.jose.sales.api.model.response.CreatedKardexResponse;
import com.jose.sales.api.model.response.KardexSimpleInfo;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-kardex")
public interface KardexClient {
  @PostMapping(path = "/kardex")
  CreatedKardexResponse saveSaleIntoKardex(
    @RequestBody List<CreateKardexRequest> request
  );

  @GetMapping(path = "/kardex/simple-info")
  List<KardexSimpleInfo> getKardexSimpleInfo(@RequestParam List<Integer> ids);
}
