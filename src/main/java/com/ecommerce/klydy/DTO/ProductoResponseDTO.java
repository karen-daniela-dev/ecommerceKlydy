package com.ecommerce.klydy.DTO;

import com.ecommerce.klydy.model.Categoria;
import com.ecommerce.klydy.model.Marca;
import com.ecommerce.klydy.model.Producto;
import com.ecommerce.klydy.model.Uso;

public class ProductoResponseDTO {


    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;
    private Categoria categoria;
    private Marca marca;
    private Uso uso;

    public ProductoResponseDTO() {}

    public ProductoResponseDTO(Long id, String nombre, Double precio, Integer stock,
                               Categoria categoria, Marca marca, Uso uso) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.marca = marca;
        this.uso = uso;
    }

    public static ProductoResponseDTO desde(Producto p) {
        return new ProductoResponseDTO(
                p.getIdProducto(),
                p.getNombre(),
                p.getPrecio(),
                p.getStock(),
                p.getCategoria(),
                p.getMarca(),
                p.getUso()
        );
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Uso getUso() {
        return uso;
    }

    public void setUso(Uso uso) {
        this.uso = uso;
    }
}
