package com.ecommerce.klydy.DTO;

import com.ecommerce.klydy.model.DetalleCompra;

public class DetalleCompraResponseDTO {

    private String productoNombre;
    private Integer cantidad;
    private Double precioUnitario;

    public DetalleCompraResponseDTO() {}


    public static DetalleCompraResponseDTO desde(DetalleCompra d) {
        DetalleCompraResponseDTO dto = new DetalleCompraResponseDTO();
        dto.productoNombre = d.getProducto().getNombre();
        dto.cantidad = d.getCantidad();
        dto.precioUnitario = d.getPrecioUnitario();
        return dto;
    }

    // getters y setters

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
