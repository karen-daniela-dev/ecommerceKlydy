package com.ecommerce.klydy.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CompraRequestDTO {

    @NotNull
    private Long usuarioId;

    @NotEmpty
    private List<DetalleCompraRequestDTO> detalles;

    public CompraRequestDTO() {}

    // getters y setters

    public @NotNull Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(@NotNull Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public @NotEmpty List<DetalleCompraRequestDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(@NotEmpty List<DetalleCompraRequestDTO> detalles) {
        this.detalles = detalles;
    }
}
