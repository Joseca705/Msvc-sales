package com.jose.sales.infraestructure.service;

import com.jose.sales.api.model.request.CreateKardexRequest;
import com.jose.sales.api.model.request.CreateSaleDto;
import com.jose.sales.api.model.response.CreatedSaleResponse;
import com.jose.sales.api.model.response.CurrentAmountBatchKardex;
import com.jose.sales.api.model.response.PurchasePriceBatchStore;
import com.jose.sales.domain.entity.Sale;
import com.jose.sales.domain.entity.SaleDetail;
import com.jose.sales.domain.repository.SaleDetailRepository;
import com.jose.sales.domain.repository.SaleRepository;
import com.jose.sales.infraestructure.abstract_service.ISaleService;
import com.jose.sales.infraestructure.client.KardexClient;
import com.jose.sales.infraestructure.client.StoreClient;
import com.jose.sales.infraestructure.exception.ProductNotFoundException;
import com.jose.sales.infraestructure.exception.ProductOverratedException;
import com.jose.sales.infraestructure.exception.ProductUnderratedException;
import com.jose.sales.infraestructure.helper.UserIdJwtHelper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SaleService implements ISaleService {

  private final SaleRepository saleRepository;
  private final SaleDetailRepository saleDetailRepository;
  private final KardexClient kardexClient;
  private final StoreClient storeClient;
  private final UserIdJwtHelper jwtHelper;

  @Override
  @Transactional
  public CreatedSaleResponse create(List<CreateSaleDto> request) {
    // Gettig only the batch ids
    List<Integer> batchIds = request
      .stream()
      .map(CreateSaleDto::getBatchId)
      .toList();

    // Making a request to get the current amount of products
    List<CurrentAmountBatchKardex> listOfAmounts =
      this.kardexClient.getCurrentAmountBatchKardex(batchIds);
    Map<Integer, Integer> amounts = listOfAmounts
      .stream()
      .collect(
        Collectors.toMap(
          CurrentAmountBatchKardex::getBatchId,
          CurrentAmountBatchKardex::getBalanceAmount
        )
      );

    // Making a request to get the purchse price of batchs
    List<PurchasePriceBatchStore> listsOfPrices =
      this.storeClient.getPurchasePriceBatchStore(batchIds);
    Map<Integer, BigDecimal> prices = listsOfPrices
      .stream()
      .collect(
        Collectors.toMap(
          PurchasePriceBatchStore::getId,
          PurchasePriceBatchStore::getPurchasePrice
        )
      );

    // Validations
    request.forEach(saleDetail -> {
      // Validate the 75%
      BigDecimal overrated = prices
        .get(saleDetail.getBatchId())
        .multiply(new BigDecimal(1.75));
      if (
        saleDetail.getUnitSalePrice().compareTo(overrated) == 1
      ) throw new ProductOverratedException(saleDetail.getProductId());

      BigDecimal underrated = prices
        .get(saleDetail.getBatchId())
        .multiply(new BigDecimal(0.25));
      if (
        saleDetail.getUnitSalePrice().compareTo(underrated) == -1
      ) throw new ProductUnderratedException(saleDetail.getProductId());
    });

    // Getting the current userid
    Integer userId = Integer.parseInt(this.jwtHelper.getCurrentUserId());

    // Saving a new pre Sale
    Sale preSale = new Sale(
      LocalDateTime.now(),
      new BigDecimal(0),
      "PRE-SALE",
      userId
    );

    Sale sale = this.saleRepository.save(preSale);

    // Creating a list of new sale datils to save
    List<SaleDetail> saleDetails = request
      .stream()
      .map(req -> {
        return new SaleDetail(
          req.getAmount(),
          req.getUnitSalePrice(),
          sale,
          req.getProductId(),
          req.getBatchId()
        );
      })
      .toList();
    saleDetails = this.saleDetailRepository.saveAll(saleDetails);

    // Updating kardex
    List<CreateKardexRequest> requests = saleDetails
      .stream()
      .map(req -> {
        int balanceAmount = amounts.get(req.getBatchId()) - req.getAmount();

        return new CreateKardexRequest(
          "ENTRADA",
          req.getAmount(),
          balanceAmount,
          req.getUnitSalesPrice(),
          "VENTA",
          req.getId(),
          req.getProductId(),
          req.getBatchId()
        );
      })
      .toList();

    this.kardexClient.saveSaleIntoKardex(requests);

    BigDecimal total = BigDecimal.ZERO;
    for (SaleDetail req : saleDetails) {
      total = total.add(req.getSubtotal());
    }

    //Updating the sale
    sale.setTotal(total);
    sale.setSaleDate(LocalDateTime.now());
    sale.setComment("SALE successfully");

    this.saleRepository.save(sale);

    return new CreatedSaleResponse("Venta realizada exitosamente.");
  }

  @Override
  public CreatedSaleResponse read(Integer id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'read'");
  }

  @Override
  public CreatedSaleResponse update(List<CreateSaleDto> request, Integer id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(Integer id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }
}
