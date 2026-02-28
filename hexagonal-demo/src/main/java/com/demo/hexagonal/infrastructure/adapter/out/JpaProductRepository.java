package com.demo.hexagonal.infrastructure.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {}
