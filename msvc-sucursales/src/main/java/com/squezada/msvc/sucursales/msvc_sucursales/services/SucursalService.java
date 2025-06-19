package com.squezada.msvc.sucursales.msvc_sucursales.services;


import com.squezada.msvc.sucursales.msvc_sucursales.models.entities.Sucursal;

import java.util.List;

public interface SucursalService {
  List<Sucursal> findAll();
  Sucursal findById(Long id);
  Sucursal save(Sucursal sucursal);
  Sucursal update(Long id, Sucursal sucursalActualizado);
  void deleteById(Long id);


}
