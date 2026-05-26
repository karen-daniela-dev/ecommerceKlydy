package com.ecommerce.klydy.repository;

import com.ecommerce.klydy.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
