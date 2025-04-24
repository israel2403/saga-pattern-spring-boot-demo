package com.huerta.orders.dao.jpa.repository;

import com.huerta.orders.dao.jpa.entity.OrderHistoryEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, UUID> {
  List<OrderHistoryEntity> findByOrderId(UUID orderId);
}
