package com.ecommerce.klydy.DTO;

import com.ecommerce.klydy.model.Categoria;
import com.ecommerce.klydy.model.Marca;
import com.ecommerce.klydy.model.Uso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductoRequestDTO {

    @NotBlank
    private String nombre;

    @NotNull
    private Integer stock;

    @NotNull
    private Double precio;

    private String urlImagen;
    private String descripcion;

    @NotNull
    private Categoria categoria;

    @NotNull
    private Marca marca;

    @NotNull
    private Uso uso;

    public ProductoRequestDTO() {}

    // getters y setters


    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    public @NotNull Integer getStock() {
        return stock;
    }

    public void setStock(@NotNull Integer stock) {
        this.stock = stock;
    }

    public @NotNull Double getPrecio() {
        return precio;
    }

    public void setPrecio(@NotNull Double precio) {
        this.precio = precio;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public @NotNull Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(@NotNull Categoria categoria) {
        this.categoria = categoria;
    }

    public @NotNull Marca getMarca() {
        return marca;
    }

    public void setMarca(@NotNull Marca marca) {
        this.marca = marca;
    }

    public @NotNull Uso getUso() {
        return uso;
    }

    public void setUso(@NotNull Uso uso) {
        this.uso = uso;
    }
}
