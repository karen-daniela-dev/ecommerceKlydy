package com.ecommerce.klydy.repository;

import com.ecommerce.klydy.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    // Spring Data deriva la consulta del nombre del méodo:
    // SELECT * FROM usuarios WHERE email = ?
    Optional<Usuario> findByCorreo(String correo);
}
