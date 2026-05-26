package com.ecommerce.klydy.service;


import com.ecommerce.klydy.DTO.ClienteRequestDTO;
import com.ecommerce.klydy.DTO.ClienteResponseDTO;
import com.ecommerce.klydy.model.Rol;
import com.ecommerce.klydy.model.Cliente;
import com.ecommerce.klydy.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    //metodos crud

    public ClienteResponseDTO crearCliente(ClienteRequestDTO dto) {

        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setCorreo(dto.getCorreo());
        cliente.setTelefono(dto.getTelefono());
        cliente.setCedula(dto.getCedula());

        cliente.setPassword((dto.getPassword()));
        cliente.setRol(Rol.CLIENTE);

        Cliente guardado = clienteRepository.save(cliente);

        return ClienteResponseDTO.desde(guardado);
    }

    public List<ClienteResponseDTO> listar() {

        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteResponseDTO> lista = new ArrayList<>();

        for (Cliente u : clientes) {
            lista.add(ClienteResponseDTO.desde(u));
        }

        return lista;
    }

    public ClienteResponseDTO obtenerPorId(Long id) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return ClienteResponseDTO.desde(cliente);
    }

    public ClienteResponseDTO actualizarCliente(Long id, ClienteRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        cliente.setNombre(dto.getNombre());
        cliente.setCorreo(dto.getCorreo());
        cliente.setTelefono(dto.getTelefono());

        Cliente actualizado = clienteRepository.save(cliente);

        return ClienteResponseDTO.desde(actualizado);
    }

    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }




}
