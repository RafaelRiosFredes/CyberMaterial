package com.squezada.msvc.sucursales.msvc_sucursales.services;


import com.squezada.msvc.sucursales.msvc_sucursales.models.Sucursal;

import java.util.List;

public interface SucursalService {
  List<Sucursal> findAll();
  Sucursal findById(Long id);
  Sucursal save(Sucursal sucursal);



}
