package com.ecommerce.klydy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String cedula;


    @Column(nullable = false)
    private String telefono;

    // Relación con Usuario
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;



    public Cliente() {}

    public Cliente(String nombre, String cedula, String telefono, Usuario usuario) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.usuario = usuario;
    }

    public Long getId() { return idCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }}