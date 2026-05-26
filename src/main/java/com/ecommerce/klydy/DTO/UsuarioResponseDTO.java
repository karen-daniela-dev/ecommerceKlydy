package com.ecommerce.klydy.DTO;

import com.ecommerce.klydy.model.Usuario;

public class UsuarioResponseDTO {

    private Long id;
    private String nombre;
    private String correo;
    private String rol;

    public UsuarioResponseDTO() {}

    public static UsuarioResponseDTO desde(Usuario u) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.id = u.getIdUsuario();
        dto.nombre = u.getNombre();
        dto.correo = u.getCorreo();
        dto.rol = u.getRol().name();
        return dto;
    }

    // getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
