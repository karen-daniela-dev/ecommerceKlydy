package com.ecommerce.klydy.repository;

import com.ecommerce.klydy.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Optional<Cliente> findByUsuarioEmail(String email);
}
