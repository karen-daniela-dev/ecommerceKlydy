package com.ecommerce.klydy.service;

import com.ecommerce.klydy.DTO.ProductoRequestDTO;
import com.ecommerce.klydy.DTO.ProductoResponseDTO;
import com.ecommerce.klydy.model.Producto;
import com.ecommerce.klydy.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    private String limpiar(String valor) {
        return valor == null ? null : valor.trim();
    }

    public ProductoResponseDTO crearProducto(ProductoRequestDTO dto) {
        if (dto.getStock() < 0) {
            throw new RuntimeException("El stock no puede ser negativo");
        }

        Producto producto = new Producto();
        producto.setNombre(limpiar(dto.getNombre()));
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setDescripcion(limpiar(dto.getDescripcion()));
        producto.setUrlImagen(limpiar(dto.getUrlImagen()));
        producto.setCategoria(dto.getCategoria());
        producto.setMarca(dto.getMarca());
        producto.setUso(dto.getUso());

        Producto guardado = productoRepository.save(producto);

        return ProductoResponseDTO.desde(guardado);
    }

    public List<ProductoResponseDTO> listar() {

        List<Producto> productos = productoRepository.findAll();
        List<ProductoResponseDTO> lista = new ArrayList<>();

        for (Producto p : productos) {
            lista.add(ProductoResponseDTO.desde(p));
        }

        return lista;
    }
    public ProductoResponseDTO obtenerPorId(Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return ProductoResponseDTO.desde(producto);
    }

    public ProductoResponseDTO actualizarProducto(Long id, ProductoRequestDTO dto) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        if (dto.getStock() < 0) {
            throw new RuntimeException("El stock no puede ser negativo");
        }

        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setDescripcion(dto.getDescripcion());
        producto.setUrlImagen(dto.getUrlImagen());
        producto.setCategoria(dto.getCategoria());
        producto.setMarca(dto.getMarca());
        producto.setUso(dto.getUso());

        Producto actualizado = productoRepository.save(producto);

        return ProductoResponseDTO.desde(actualizado);
    }
    public void eliminar(Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        productoRepository.delete(producto);
    }


}
