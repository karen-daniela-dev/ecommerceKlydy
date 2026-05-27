package com.ecommerce.klydy.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CompraRequestDTO {



    @NotEmpty
    private List<DetalleCompraRequestDTO> detalles;

    public CompraRequestDTO() {}

    // getters y setters





    public @NotEmpty List<DetalleCompraRequestDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(@NotEmpty List<DetalleCompraRequestDTO> detalles) {
        this.detalles = detalles;
    }
}
