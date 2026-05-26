package com.ecommerce.klydy.DTO;

import com.ecommerce.klydy.model.Cliente;

public class ClienteResponseDTO {

    private Long id;
    private String nombre;
    private String email;
    private String rol;

    public ClienteResponseDTO() {}

    public static ClienteResponseDTO desde(Cliente u) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.id = u.getIdCliente();
        dto.nombre = u.getNombre();
        dto.email = u.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
