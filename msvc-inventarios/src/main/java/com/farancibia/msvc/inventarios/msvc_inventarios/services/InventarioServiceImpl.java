package com.farancibia.msvc.inventarios.msvc_inventarios.services;

import com.farancibia.msvc.inventarios.msvc_inventarios.models.entities.Inventario;

public class InventarioServiceImpl implements InventarioService{
    @Override
    public Inventario findByIdSucursalAndIdProducto(Long idSucursal, Long idProducto) {
            return findByIdSucursalAndIdProducto(idSucursal, idProducto);
    }
}
