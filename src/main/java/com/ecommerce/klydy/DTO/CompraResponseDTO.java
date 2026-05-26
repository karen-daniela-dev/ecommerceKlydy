package com.ecommerce.klydy.DTO;

import com.ecommerce.klydy.model.Compra;
import com.ecommerce.klydy.model.DetalleCompra;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CompraResponseDTO {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;
    private String usuarioNombre;
    private List<DetalleCompraResponseDTO> detalles;
    private Double total;

    public CompraResponseDTO() {}

    public static CompraResponseDTO desde(Compra     compra) {

        CompraResponseDTO dto = new CompraResponseDTO();

        dto.id = compra.getIdCompra();
        dto.usuarioNombre = compra.getUsuario().getNombre();
        dto.fecha = compra.getFecha();

        List<DetalleCompraResponseDTO> lista = new ArrayList<>();
        double total = 0;
        for (DetalleCompra d : compra.getDetalles()) {
            lista.add(DetalleCompraResponseDTO.desde(d));
            total += d.getCantidad() * d.getPrecioUnitario();
        }


        dto.detalles = lista;
        dto.total = total;

        return dto;
    }



    // getters y setters
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }


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
