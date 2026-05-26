package com.ecommerce.klydy.controller;

import com.ecommerce.klydy.DTO.CompraRequestDTO;
import com.ecommerce.klydy.DTO.CompraResponseDTO;
import com.ecommerce.klydy.service.CompraService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    public CompraResponseDTO crear(@Valid @RequestBody CompraRequestDTO dto) {
        return compraService.crearCompra(dto);
    }


}
