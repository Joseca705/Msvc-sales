package com.jose.sales.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.jose.sales.api.model.request.CreateSaleDto;
import com.jose.sales.api.model.response.CreatedSaleResponse;
import com.jose.sales.api.model.response.CurrentAmountBatchKardex;
import com.jose.sales.api.model.response.PurchasePriceBatchStore;
import com.jose.sales.domain.entity.Sale;
import com.jose.sales.domain.entity.SaleDetail;
import com.jose.sales.domain.repository.SaleDetailRepository;
import com.jose.sales.domain.repository.SaleRepository;
import com.jose.sales.infraestructure.client.KardexClient;
import com.jose.sales.infraestructure.client.StoreClient;
import com.jose.sales.infraestructure.exception.ProductNotFoundException;
import com.jose.sales.infraestructure.exception.ProductOverratedException;
import com.jose.sales.infraestructure.exception.ProductUnderratedException;
import com.jose.sales.infraestructure.helper.UserIdJwtHelper;
import com.jose.sales.infraestructure.service.SaleService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SaleServiceTest {

  @Mock
  private SaleRepository saleRepository;

  @Mock
  private SaleDetailRepository saleDetailRepository;

  @Mock
  private KardexClient kardexClient;

  @Mock
  private StoreClient storeClient;

  @Mock
  private UserIdJwtHelper jwtHelper;

  @InjectMocks
  private SaleService saleService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @DisplayName("Este metodo se encarga de crear exitosamente una venta")
  @Test
  void crearVenta_Exitoso() {
    // Creamos objetos de prueba
    CreateSaleDto saleDto = new CreateSaleDto(
        1,
        1,
        2,
        BigDecimal.valueOf(150.00));
    List<CreateSaleDto> request = List.of(saleDto);

    PurchasePriceBatchStore priceInfo = new PurchasePriceBatchStore(
        1,
        BigDecimal.valueOf(100));
    when(storeClient.getPurchasePriceBatchStore(anyList())).thenReturn(
        List.of(priceInfo));

    CurrentAmountBatchKardex stockInfo = new CurrentAmountBatchKardex(1, 10);
    when(kardexClient.getCurrentAmountBatchKardex(anyList())).thenReturn(
        List.of(stockInfo));

    // Creamos un usuario de auditoria de test
    when(jwtHelper.getCurrentUserId()).thenReturn("42");

    Sale savedSale = new Sale(
        LocalDateTime.now(),
        BigDecimal.ZERO,
        "PRE-SALE",
        42);
    savedSale.setId(1);
    when(saleRepository.save(any(Sale.class))).thenReturn(savedSale);

    SaleDetail saleDetail = new SaleDetail(
        2,
        BigDecimal.valueOf(150),
        savedSale,
        1,
        1);
    saleDetail.setSubtotal(
        saleDetail
            .getUnitSalesPrice()
            .multiply(BigDecimal.valueOf(saleDetail.getAmount())));
    when(saleDetailRepository.saveAll(anyList())).thenReturn(
        List.of(saleDetail));

    // Comenzamos la prueba
    CreatedSaleResponse response = saleService.create(request);

    // Assert
    assertNotNull(response);
    assertEquals("Venta realizada exitosamente.", response.getMessage());
  }

  @DisplayName("Este metodo se encarga de lanzar una excepcion cuando un producto no exista o no se encuentre su stock.")
  @Test
  void crearVenta_ThrowsProductNotFoundException() {
    // Creamos los objetos de prueba
    CreateSaleDto saleDto = new CreateSaleDto(
        1,
        1,
        2,
        BigDecimal.valueOf(150.00));
    when(storeClient.getPurchasePriceBatchStore(anyList())).thenReturn(
        List.of());

    // Act & Assert
    assertThrows(ProductNotFoundException.class, () -> saleService.create(List.of(saleDto)));
  }

  @DisplayName("Este metodo lanza una excepcion cuando el precio de venta sea mayor al 75% del precio de compra.")
  @Test
  void crearVenta_ThrowsProductOverratedException() {
    // Creamos objetos de prueba con el precio mas caro que el 75%
    CreateSaleDto saleDto = new CreateSaleDto(1, 1, 2, BigDecimal.valueOf(200));
    List<CreateSaleDto> request = List.of(saleDto);

    // Creamos el objeto con el precio original
    PurchasePriceBatchStore priceInfo = new PurchasePriceBatchStore(
        1,
        BigDecimal.valueOf(100));

    when(storeClient.getPurchasePriceBatchStore(anyList())).thenReturn(
        List.of(priceInfo));

    when(kardexClient.getCurrentAmountBatchKardex(anyList())).thenReturn(
        List.of(new CurrentAmountBatchKardex(1, 10)));

    // Assert
    assertThrows(ProductOverratedException.class, () -> saleService.create(request));
  }

  @DisplayName("Este metodo lanza una excepcion cuando el precio de venta sea menor al 75% del precio de compra.")
  @Test
  void crearVenta_ThrowsProductUnderratedException() {
    // Creamos objetos de prueba con el precio menos caro que el 75%
    CreateSaleDto saleDto = new CreateSaleDto(1, 1, 2, BigDecimal.valueOf(20));
    List<CreateSaleDto> request = List.of(saleDto);

    // Creamos el objeto con el precio original
    PurchasePriceBatchStore priceInfo = new PurchasePriceBatchStore(
        1,
        BigDecimal.valueOf(100));
    when(storeClient.getPurchasePriceBatchStore(anyList())).thenReturn(
        List.of(priceInfo));

    when(kardexClient.getCurrentAmountBatchKardex(anyList())).thenReturn(
        List.of(new CurrentAmountBatchKardex(1, 10)));

    // Assert
    assertThrows(ProductUnderratedException.class, () -> saleService.create(request));
  }
}
