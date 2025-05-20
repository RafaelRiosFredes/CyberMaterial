package com.farancibia.msvc.inventarios.msvc_inventarios.services;

import com.farancibia.msvc.inventarios.msvc_inventarios.models.entities.Inventario;

public interface InventarioService {

    Inventario findByIdSucursalAndIdProducto(Long idSucursal, Long idProducto);
}
