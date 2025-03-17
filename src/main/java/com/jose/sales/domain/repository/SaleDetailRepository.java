package com.jose.sales.domain.repository;

import com.jose.sales.api.model.projection.TopSellingProductsProjection;
import com.jose.sales.domain.entity.SaleDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository
  extends JpaRepository<SaleDetail, Integer> {
  @Query(
    value = """
    SELECT p.id, p.name, sum(sd.amount) as total_amount
    FROM products p
    INNER JOIN sales_details sd ON p.id = sd.product_id
    GROUP BY p.id
    ORDER BY total_amount desc
    LIMIT :limit
      """,
    nativeQuery = true
  )
  List<Object[]> findTopSellingProductsByLimit(@Param("limit") Integer limit);
}
