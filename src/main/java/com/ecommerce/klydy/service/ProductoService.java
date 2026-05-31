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
    private final CloudinaryService cloudinaryService;


    @Autowired
    public ProductoService(ProductoRepository productoRepository,CloudinaryService cloudinaryService) {
        this.productoRepository = productoRepository;
        this.cloudinaryService = cloudinaryService;
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

        String urlAnterior = producto.getUrlImagen();
        String urlNueva = dto.getUrlImagen();


        if (urlAnterior != null && (urlNueva == null || !urlAnterior.equals(urlNueva))) {

            if (urlAnterior.contains("cloudinary")) {
                String publicId = cloudinaryService.extraerPublicId(urlAnterior);

                if (publicId != null) {
                    cloudinaryService.eliminarImagen(publicId);
                }
            }
        }


        // actualizar datos
        producto.setNombre(limpiar(dto.getNombre()));
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setDescripcion(limpiar(dto.getDescripcion()));
        producto.setUrlImagen(limpiar(urlNueva));
        producto.setCategoria(dto.getCategoria());
        producto.setMarca(dto.getMarca());
        producto.setUso(dto.getUso());

        Producto actualizado = productoRepository.save(producto);

        return ProductoResponseDTO.desde(actualizado);
    }



    public void eliminar(Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        String url = producto.getUrlImagen();

        if (url != null && url.contains("cloudinary")) {
            String publicId = cloudinaryService.extraerPublicId(url);

            if (publicId != null) {
                cloudinaryService.eliminarImagen(publicId);
            }
        }

        productoRepository.delete(producto);
    }


}
