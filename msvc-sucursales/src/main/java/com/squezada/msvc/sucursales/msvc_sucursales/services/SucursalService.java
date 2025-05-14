package com.squezada.msvc.sucursales.msvc_sucursales.services;


import com.squezada.msvc.sucursales.msvc_sucursales.models.Sucursal;
import com.squezada.msvc.sucursales.msvc_sucursales.repositories.SucursalRepository;

import java.util.List;

public interface SucursalService {
    List<SucursalRepository> findAll();
    Sucursal findById(Long id);
    Sucursal save(Sucursal sucursal);

}
