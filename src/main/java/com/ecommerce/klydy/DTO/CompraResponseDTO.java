package com.ecommerce.klydy.DTO;

import com.ecommerce.klydy.model.Compra;
import com.ecommerce.klydy.model.DetalleCompra;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CompraResponseDTO {

    private Long id;
    private LocalDateTime fecha;
    private String usuarioNombre;
    private List<DetalleCompraResponseDTO> detalles;

    public CompraResponseDTO() {}

    public static CompraResponseDTO desde(Compra     compra) {

        CompraResponseDTO dto = new CompraResponseDTO();

        dto.id = compra.getIdCompra();
        dto.usuarioNombre = compra.getUsuario().getNombre();

        List<DetalleCompraResponseDTO> lista = new ArrayList<>();

        for (DetalleCompra d : compra.getDetalles()) {
            lista.add(DetalleCompraResponseDTO.desde(d));
        }

        dto.detalles = lista;

        return dto;
    }



    // getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public List<DetalleCompraResponseDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCompraResponseDTO> detalles) {
        this.detalles = detalles;
    }
}
