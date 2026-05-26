package com.ecommerce.klydy.service;


import com.ecommerce.klydy.DTO.UsuarioRequestDTO;
import com.ecommerce.klydy.DTO.UsuarioResponseDTO;
import com.ecommerce.klydy.model.Rol;
import com.ecommerce.klydy.model.Usuario;
import com.ecommerce.klydy.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    //metodos crud

    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setTelefono(dto.getTelefono());
        usuario.setCedula(dto.getCedula());

        usuario.setPassword((dto.getPassword()));
        usuario.setRol(Rol.CLIENTE);

        Usuario guardado = usuarioRepository.save(usuario);

        return UsuarioResponseDTO.desde(guardado);
    }

    public List<UsuarioResponseDTO> listar() {

        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDTO> lista = new ArrayList<>();

        for (Usuario u : usuarios) {
            lista.add(UsuarioResponseDTO.desde(u));
        }

        return lista;
    }

    public UsuarioResponseDTO obtenerPorId(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return UsuarioResponseDTO.desde(usuario);
    }

    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO dto) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setTelefono(dto.getTelefono());

        Usuario actualizado = usuarioRepository.save(usuario);

        return UsuarioResponseDTO.desde(actualizado);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }




}
