package com.ecommerce.klydy.controller;

import com.ecommerce.klydy.DTO.ProductoRequestDTO;
import com.ecommerce.klydy.DTO.ProductoResponseDTO;
import com.ecommerce.klydy.service.ProductoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping

    public ProductoResponseDTO crear(@Valid @RequestBody ProductoRequestDTO dto) {
        return productoService.crearProducto(dto);
    }


    @GetMapping
    public List<ProductoResponseDTO> listar() {
        return productoService.listar();
    }


    @GetMapping("/{id}")
    public ProductoResponseDTO obtener(@PathVariable Long id) {
        return productoService.obtenerPorId(id);
    }


    @PutMapping("/{id}")
    public ProductoResponseDTO actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequestDTO dto) {

        return productoService.actualizarProducto(id, dto);
    }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
    }
}
