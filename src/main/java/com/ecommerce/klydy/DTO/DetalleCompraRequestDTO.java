package com.ecommerce.klydy.DTO;

import jakarta.validation.constraints.NotNull;

public class DetalleCompraRequestDTO {

    @NotNull
    private Long productoId;

    @NotNull
    private Integer cantidad;

    public DetalleCompraRequestDTO() {}

    // getters y setters


    public @NotNull Long getProductoId() {
        return productoId;
    }

    public void setProductoId(@NotNull Long productoId) {
        this.productoId = productoId;
    }

    public @NotNull Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(@NotNull Integer cantidad) {
        this.cantidad = cantidad;
    }
}
