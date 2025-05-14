package com.squezada.msvc.sucursales.msvc_sucursales.services;


import com.squezada.msvc.sucursales.msvc_sucursales.dtos.SucursalDTO;
import com.squezada.msvc.sucursales.msvc_sucursales.models.Sucursal;
import com.squezada.msvc.sucursales.msvc_sucursales.repositories.SucursalRepository;

import java.util.List;
import java.util.Optional;

public interface SucursalService {
  SucursalDTO save(SucursalDTO dto);
  List<SucursalDTO> findAll();
  SucursalDTO findById(Long id);
  void deleteById(Long id);




}
