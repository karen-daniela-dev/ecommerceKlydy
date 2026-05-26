package com.ecommerce.klydy.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CompraRequestDTO {

    @NotNull
    private Long clienteId;

    @NotEmpty
    private List<DetalleCompraRequestDTO> detalles;

    public CompraRequestDTO() {}

    // getters y setters

    public @NotNull Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(@NotNull Long clienteId) {
        this.clienteId = clienteId;
    }

    public @NotEmpty List<DetalleCompraRequestDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(@NotEmpty List<DetalleCompraRequestDTO> detalles) {
        this.detalles = detalles;
    }
}
