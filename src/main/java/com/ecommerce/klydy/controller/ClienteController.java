package com.ecommerce.klydy.controller;

import com.ecommerce.klydy.DTO.ClienteRequestDTO;
import com.ecommerce.klydy.DTO.ClienteResponseDTO;
import com.ecommerce.klydy.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    //metodos crud

    @PostMapping
    public ClienteResponseDTO crear(@Valid @RequestBody ClienteRequestDTO dto) {
        return clienteService.crearCliente(dto);
    }

    @GetMapping
    public List<ClienteResponseDTO> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO obtenerPorID(@PathVariable Long id) {
        return clienteService.obtenerPorId(id);
    }
    @PutMapping("/{id}")
    public ClienteResponseDTO actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO dto) {

        return clienteService.actualizarCliente(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
    }

}
