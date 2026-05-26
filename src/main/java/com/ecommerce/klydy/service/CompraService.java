package com.ecommerce.klydy.service;

import com.ecommerce.klydy.DTO.CompraRequestDTO;
import com.ecommerce.klydy.DTO.CompraResponseDTO;
import com.ecommerce.klydy.DTO.DetalleCompraRequestDTO;
import com.ecommerce.klydy.model.Compra;
import com.ecommerce.klydy.model.DetalleCompra;
import com.ecommerce.klydy.model.Producto;
import com.ecommerce.klydy.model.Usuario;
import com.ecommerce.klydy.repository.CompraRepository;
import com.ecommerce.klydy.repository.ProductoRepository;
import com.ecommerce.klydy.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public CompraService(CompraRepository compraRepository,
                         UsuarioRepository usuarioRepository,
                         ProductoRepository productoRepository) {
        this.compraRepository = compraRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }

    public CompraResponseDTO crearCompra(CompraRequestDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Compra compra = new Compra();
        compra.setUsuario(usuario);
        compra.setFecha(LocalDateTime.now());

        List<DetalleCompra> detalles = new ArrayList<>();

        for (DetalleCompraRequestDTO d : dto.getDetalles()) {

            Producto producto = productoRepository.findById(d.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (producto.getStock() < d.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + producto.getNombre());
            }

            DetalleCompra detalle = new DetalleCompra();
            detalle.setProducto(producto);
            detalle.setCantidad(d.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setCompra(compra);

            producto.setStock(producto.getStock() - d.getCantidad());

            detalles.add(detalle);
        }

        compra.setDetalles(detalles);

        Compra guardada = compraRepository.save(compra);

        return CompraResponseDTO.desde(guardada);
    }
}
