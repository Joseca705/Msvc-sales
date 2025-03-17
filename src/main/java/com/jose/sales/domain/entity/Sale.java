package com.jose.sales.domain.entity;

import com.jose.sales.domain.constant.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sales")
public class Sale extends BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "sale_date", nullable = false)
  private LocalDateTime saleDate;

  @Column(nullable = false, precision = 38, scale = 4)
  private BigDecimal total;

  @Column(nullable = true)
  private String comment;

  @Column(name = "user_id", nullable = false)
  private Integer userId;

  @OneToMany(mappedBy = "sale", fetch = FetchType.LAZY)
  private List<SaleDetail> saleDetails;

  public Sale(
    LocalDateTime saleDate,
    BigDecimal total,
    String comment,
    Integer userId
  ) {
    this.saleDate = saleDate;
    this.total = total;
    this.comment = comment;
    this.userId = userId;
  }

  @PrePersist
  public void onPrePersist() {
    this.setStatus(Status.ACTIVE);
  }
}
