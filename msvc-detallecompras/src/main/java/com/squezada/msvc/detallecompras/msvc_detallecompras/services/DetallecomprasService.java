package com.squezada.msvc.detallecompras.msvc_detallecompras.services;

import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.DetalledecomprasDTO;

import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompras;


import java.util.List;

public interface DetallecomprasService {

    Detallecompras findByIdproducto(Long productoId);
    List<DetalledecomprasDTO> findAll();
    Detallecompras save(Detallecompras detallecompras);


}
