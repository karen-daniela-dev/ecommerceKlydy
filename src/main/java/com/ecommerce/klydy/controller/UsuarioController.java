package com.ecommerce.klydy.controller;

import com.ecommerce.klydy.DTO.UsuarioRequestDTO;
import com.ecommerce.klydy.DTO.UsuarioResponseDTO;
import com.ecommerce.klydy.model.Usuario;
import com.ecommerce.klydy.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    //metodos crud

    @PostMapping
    public UsuarioResponseDTO crear(@Valid @RequestBody UsuarioRequestDTO dto) {
        return usuarioService.crearUsuario(dto);
    }

    @GetMapping
    public List<UsuarioResponseDTO> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO obtenerPorID(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }
    @PutMapping("/{id}")
    public UsuarioResponseDTO actualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDTO dto) {

        return usuarioService.actualizarUsuario(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }

}
