package com.farancibia.msvc.inventarios.msvc_inventarios.services;

import com.farancibia.msvc.inventarios.msvc_inventarios.dtos.InventarioDTO;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.entities.Inventario;
import jakarta.validation.Valid;

import java.util.List;

public interface InventarioService {

    InventarioDTO findByIdSucursalAndIdProducto(Long idSucursal, Long idProducto);
    List<InventarioDTO> findAll();
    Inventario save(@Valid Inventario inventario);
}
