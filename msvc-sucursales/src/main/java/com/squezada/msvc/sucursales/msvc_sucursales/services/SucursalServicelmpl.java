package com.squezada.msvc.sucursales.msvc_sucursales.services;

import com.squezada.msvc.sucursales.msvc_sucursales.exceptions.SucursalException;
import com.squezada.msvc.sucursales.msvc_sucursales.models.entities.Sucursal;
import com.squezada.msvc.sucursales.msvc_sucursales.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalServicelmpl implements SucursalService {

    @Autowired SucursalRepository sucursalRepository;

    @Override
    public List<Sucursal> findAll() {
        return this.sucursalRepository.findAll();
    }

    @Override
    public Sucursal findById(Long id) {
        return this.sucursalRepository.findById(id).orElseThrow(
                () -> new SucursalException("La sucursal con el id: " +id+" no se encuentra en la base de datos")
        );
    }

    @Override
    public Sucursal save(Sucursal sucursal) {
        // Guardar directamente el objeto recibido con los datos válidos
        return this.sucursalRepository.save(sucursal);
    }


    @Override
    public Sucursal update(Long id, Sucursal sucursalDetails) {
        Sucursal sucursal = this.findById(id); // busca la sucursal o lanza excepción

        // Actualiza los campos que quieres modificar
        sucursal.setHorario(sucursalDetails.getHorario());
        sucursal.setDireccion(sucursalDetails.getDireccion());


        return this.sucursalRepository.save(sucursal);
    }

    @Override
    public void deleteById(Long id) {
        this.sucursalRepository.deleteById(id);
    }
}


