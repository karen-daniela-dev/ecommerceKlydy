package com.ecommerce.klydy.service;

import com.ecommerce.klydy.DTO.CompraRequestDTO;
import com.ecommerce.klydy.DTO.CompraResponseDTO;
import com.ecommerce.klydy.DTO.DetalleCompraRequestDTO;
import com.ecommerce.klydy.model.*;
import com.ecommerce.klydy.repository.CompraRepository;
import com.ecommerce.klydy.repository.ProductoRepository;
import com.ecommerce.klydy.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public CompraService(CompraRepository compraRepository,
                         ClienteRepository clienteRepository,
                         ProductoRepository productoRepository) {
        this.compraRepository = compraRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public CompraResponseDTO crearCompra(CompraRequestDTO dto, Usuario usuario) {

        Cliente cliente = clienteRepository
                .findByUsuarioEmail(usuario.getEmail())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Compra compra = new Compra();
        compra.setCliente(cliente);
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
