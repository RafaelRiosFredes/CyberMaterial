package com.farancibia.msvc.inventarios.msvc_inventarios.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    private Long idSucursal;
    private Long idProducto;
    private Integer stock;
}
