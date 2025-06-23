package com.squezada.msvc.detallecompras.msvc_detallecompras.services;

import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.DetalledecomprasDTO;

import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompras;


import java.util.List;

public interface DetallecomprasService {


    DetalledecomprasDTO findById(Long id);
    Detallecompras save(Detallecompras detallecompras);
    List<DetalledecomprasDTO> findByIdBoleta(Long idBoleta);
    Detallecompras update(Long id, Detallecompras detalleActualizado);
    void deleteById(Long id);
    List<Detallecompras> findAll();
}
