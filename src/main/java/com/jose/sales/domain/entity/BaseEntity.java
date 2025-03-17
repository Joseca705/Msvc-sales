package com.jose.sales.domain.entity;

import com.jose.sales.domain.constant.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  @Column(name = "created_at", updatable = false, nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private LocalDateTime createdAt;

  @Column(name = "updated_at", insertable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private LocalDateTime updatedAt;

  @Column(name = "created_by", updatable = false, nullable = false)
  @CreatedBy
  private Integer createdBy;

  @Column(name = "updated_by", insertable = false)
  @LastModifiedBy
  private Integer updatedBy;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Status status;
}
