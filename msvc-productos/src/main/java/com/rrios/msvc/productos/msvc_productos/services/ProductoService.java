package com.rrios.msvc.productos.msvc_productos.services;

import com.rrios.msvc.productos.msvc_productos.models.entities.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> findAll();
    Producto findById(Long id);
    Producto save(Producto producto);
}
