package com.jose.sales.domain.entity;

import com.jose.sales.domain.constant.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sales_details")
public class SaleDetail extends BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private Integer amount;

  @Column(
    name = "unit_sales_price",
    nullable = false,
    precision = 38,
    scale = 4
  )
  private BigDecimal unitSalesPrice;

  @Column(name = "subtotal", nullable = false, precision = 38, scale = 4)
  private BigDecimal subtotal;

  @ManyToOne
  @JoinColumn(name = "sale_id", referencedColumnName = "id", nullable = false)
  private Sale sale;

  @Column(name = "product_id", nullable = false)
  private Integer productId;

  @Column(name = "batch_id", nullable = false)
  private Integer batchId;

  public SaleDetail(
    Integer amount,
    BigDecimal unitSalesPrice,
    Sale sale,
    Integer productId,
    Integer batchId
  ) {
    this.amount = amount;
    this.unitSalesPrice = unitSalesPrice;
    this.sale = sale;
    this.productId = productId;
    this.batchId = batchId;
  }

  @PrePersist
  public void onPrePersist() {
    this.setStatus(Status.ACTIVE);
    this.subtotal = unitSalesPrice.multiply(new BigDecimal(this.amount));
  }
}
