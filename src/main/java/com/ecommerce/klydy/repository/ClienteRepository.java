package com.ecommerce.klydy.repository;

import com.ecommerce.klydy.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
