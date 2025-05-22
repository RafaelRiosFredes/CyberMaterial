package com.rrios.msvc.productos.msvc_productos.services;

import com.rrios.msvc.productos.msvc_productos.clients.InventarioClientRest;
import com.rrios.msvc.productos.msvc_productos.clients.SucursalClientRest;
import com.rrios.msvc.productos.msvc_productos.exceptions.ProductoException;
import com.rrios.msvc.productos.msvc_productos.models.Inventario;
import com.rrios.msvc.productos.msvc_productos.models.Sucursal;
import com.rrios.msvc.productos.msvc_productos.models.entities.Producto;
import com.rrios.msvc.productos.msvc_productos.repositories.ProductoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    private InventarioClientRest inventarioClientRest;
    private SucursalClientRest sucursalClientRest;


    @Override
    public List<Producto> findAll() {
        return this.productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return this.productoRepository.findById(id).orElseThrow(
                () -> new ProductoException("El producto con id "+id+" no se encuentra en la base de datos")
        );
    }

    @Override
    public Producto save(Producto producto) {
        return this.productoRepository.save(producto);
    }
}














