package com.squezada.msvc.sucursales.msvc_sucursales.services;

import com.squezada.msvc.sucursales.msvc_sucursales.dtos.SucursalDTO;
import com.squezada.msvc.sucursales.msvc_sucursales.models.Sucursal;
import com.squezada.msvc.sucursales.msvc_sucursales.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

   @Override
    public List<Sucursal> findAll() {
       return sucursalRepository.findAll();

   }

   @Override
    public Sucursal findById(long id){
       return sucursalRepository.findById(id).orElseThrow(
               () -> new SucursalException("La sucursal con id" + id + " no se encuentra en la base de datos")
       );


   }

   @Override
    public Sucursal save(Sucursal sucursal){
       return sucursalRepository.save(sucursal);
   }

}
