package com.jose.sales.domain.repository;

import com.jose.sales.domain.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository
  extends JpaRepository<SaleDetail, Integer> {}
