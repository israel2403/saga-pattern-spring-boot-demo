package com.appsdeveloperblog.product_service.dao.jpa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appsdeveloperblog.product_service.dao.jpa.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}
