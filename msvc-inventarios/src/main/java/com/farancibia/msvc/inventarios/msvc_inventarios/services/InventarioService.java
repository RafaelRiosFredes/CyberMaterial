package com.farancibia.msvc.inventarios.msvc_inventarios.services;

import com.farancibia.msvc.inventarios.msvc_inventarios.dtos.InventarioDTO;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.entities.Inventario;

import java.util.List;

public interface InventarioService {

    Inventario findByIdSucursalAndIdProducto(Long idSucursal, Long idProducto);
    List<InventarioDTO> findAll();
    Inventario save(Inventario inventario);
}
