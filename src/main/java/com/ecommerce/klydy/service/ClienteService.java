package com.ecommerce.klydy.service;


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




    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }




}
